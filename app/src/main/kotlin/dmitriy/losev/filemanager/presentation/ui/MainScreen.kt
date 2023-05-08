package dmitriy.losev.filemanager.presentation.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dmitriy.losev.filemanager.presentation.navigation.Navigation
import dmitriy.losev.filemanager.presentation.ui.file.TopBar
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen(viewModel: FileViewModel = koinViewModel()) {

    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        TopBar(viewModel = viewModel)
    }) { paddingValues ->
        Navigation(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = paddingValues), viewModel = viewModel
        )
    }
}