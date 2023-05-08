package dmitriy.losev.filemanager.presentation.ui.file

import android.os.Environment
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.SdCard
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitriy.losev.core.core.theme.SurfaceLight
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel
import org.koin.androidx.compose.koinViewModel

private val STORAGE = Environment.getExternalStorageDirectory().absolutePath

@Composable
fun StorageBar(
    path: String,
    navController: NavController,
    viewModel: FileViewModel
) {
    PathToFiles(path = path, viewModel = viewModel, navController = navController)
}

@Composable
private fun PathToFiles(path: String, navController: NavController, viewModel: FileViewModel) {

    val listOfPath = path.removePrefix(STORAGE).split('/').filter { it.isNotBlank() }

    val state = rememberLazyListState()

    LaunchedEffect(key1 = Unit) {
        state.scrollToItem(index = listOfPath.size * 2)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
            .border(
                width = 2.dp,
                brush = Brush.verticalGradient(listOf(SurfaceLight, Color.Black)),
                shape = RoundedCornerShape(bottomEnd = 16.dp, bottomStart = 16.dp)
            )
    ) {
        LazyRow(
            state = state,
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            item {
                MenuIcon(
                    imageVector = Icons.Default.SdCard,
                    modifier = Modifier.clickable {
                        viewModel.onChangePath(
                            newPath = STORAGE,
                            navController = navController
                        )
                    })
            }

            listOfPath.fold(STORAGE) { total, next ->

                val totalPath = "$total/$next"

                item {
                    MenuIcon(imageVector = Icons.Default.NavigateNext)
                }

                item {
                    PathText(
                        path = next,
                        modifier = Modifier.clickable {
                            viewModel.onChangePath(
                                newPath = totalPath,
                                navController = navController
                            )
                        })
                }
                totalPath
            }
        }
        Divider()
    }
}

@Composable
private fun PathText(path: String, modifier: Modifier) {
    Text(
        text = path,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Black,
        modifier = modifier
    )
}

@Composable
private fun MenuIcon(imageVector: ImageVector, modifier: Modifier = Modifier) {
    Icon(
        imageVector = imageVector,
        contentDescription = "",
        tint = Color.DarkGray,
        modifier = modifier.size(36.dp)
    )
}