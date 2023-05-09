package dmitriy.losev.filemanager.presentation.ui.file

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileScreen(
    path: String,
    viewModel: FileViewModel,
    navController: NavController
) {

    val files by viewModel.files.collectAsState()

    LaunchedEffect(key1 = path) {
        withContext(Dispatchers.Default) {
            viewModel.loadingFiles(path = path)
            viewModel.sortingFiles()
            viewModel.checkChangingFile()
        }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        stickyHeader {
            StorageBar(path = path, navController = navController, viewModel = viewModel)
        }

        itemsIndexed(files) { index, fileModel ->

            FileItem(fileModel = fileModel, viewModel = viewModel, navController = navController)

            if (index < files.lastIndex) {
                Divider(
                    color = Color.DarkGray,
                    thickness = 1.dp,
                    modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                )
            }
        }
    }
}

