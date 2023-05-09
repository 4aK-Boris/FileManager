package dmitriy.losev.filemanager.domain.usecases

import android.app.Application
import android.content.Intent
import androidx.core.content.FileProvider
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

class IntentUseCases(private val application: Application) : BaseUseCase() {

    suspend fun openFile(path: String): Result<Unit> = safeCall {
        with(application) {
            val intent = createFileIntent(path = path, action = Intent.ACTION_VIEW)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            withContext(Dispatchers.Main) {
                startActivity(intent)
            }
        }
    }

    suspend fun sharedFile(path: String): Result<Unit> = safeCall {
        with(application) {
            val intent = createFileIntent(path = path, action = Intent.ACTION_SEND)
            intent.putExtra(Intent.EXTRA_TEXT, "Файл взят из FileManager")
            intent.putExtra(Intent.EXTRA_STREAM, intent.data)
            withContext(Dispatchers.Main) {
                startActivity(Intent.createChooser(intent, "Поделиться файлом").apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                })
            }
        }
    }

    private fun Application.createFileIntent(path: String, action: String): Intent {
        val intent = Intent(action)
        val uri = FileProvider.getUriForFile(
                this,
                applicationContext.packageName + ".provider",
                File(path)
        )
        intent.setDataAndType(uri, contentResolver.getType(uri))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        return intent
    }
}