package dmitriy.losev.filemanager.core.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import dmitriy.losev.filemanager.domain.usecases.FileUseCases
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HashFilesWork(
    appContext: Context,
    workerParams: WorkerParameters,
) : Worker(appContext, workerParams), KoinComponent {

    private val fileUseCases by inject<FileUseCases>()

    override fun doWork(): Result {
        val path = inputData.getString(PATH_KEY) ?: return Result.failure()
        fileUseCases.createHashFiles(path = path)
        return Result.success()
    }

    companion object {
        const val PATH_KEY = "path"
    }
}

