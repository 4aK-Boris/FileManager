package dmitriy.losev.filemanager.core

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.filemanager.core.MainViewModel
import dmitriy.losev.filemanager.presentation.ui.TestScreen
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mpei.core.core.BaseActivity

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()
    override val baseViewModel: BaseViewModel by lazy { viewModel }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @Composable
    override fun UI() {

        TestScreen()
    }
}
