package dmitriy.losev.filemanager.core

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat
import dmitriy.losev.core.core.BaseActivity
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.filemanager.presentation.ui.MainScreen
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    private val fileViewModel by viewModel<FileViewModel>()

    private val path: String = Environment.getExternalStorageDirectory().absolutePath

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permissionStatus =
            ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)

        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            fileViewModel.createHashFiles(path = path)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    override fun UI() {

        MainScreen(viewModel = fileViewModel)
    }
}
