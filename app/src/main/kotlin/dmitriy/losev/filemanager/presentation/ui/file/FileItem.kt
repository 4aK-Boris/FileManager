package dmitriy.losev.filemanager.presentation.ui.file

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dmitriy.losev.core.core.theme.cardCollapsedBackgroundColor
import dmitriy.losev.filemanager.core.file.FileExtension
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FileItem(
    fileItem: FileModel,
    navController: NavController,
    viewModel: FileViewModel
) {

    val gradientColor = if (fileItem.isChanged) {
        cardCollapsedBackgroundColor
    } else {
        Color.White
    }

    val onClick = {
        if (fileItem.extension !== FileExtension.FOLDER) {
            viewModel.openFile(fileItem.path)
        } else {
            viewModel.onChangePath(
                newPath = fileItem.path,
                navController = navController
            )
        }
    }

    val onLongClick = {
        if (fileItem.extension !== FileExtension.FOLDER) {
            viewModel.sharedFile(fileItem.path)
        }
    }

    Row(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.White,
                        gradientColor,
                        Color.White
                    )
                )
            )
            .padding(horizontal = 16.dp)
            .height(height = 56.dp)
            .combinedClickable(onClick = {
                onClick()
            }, onLongClick = {
                onLongClick()
            }),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FileIcon(drawable = painterResource(id = fileItem.extension.idRes))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp, top = 4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MainText(text = fileItem.name)

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SecondaryText(text = fileItem.size)

                SecondaryText(text = fileItem.dateOfCreation)
            }
        }
    }
}

@Composable
private fun MainText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        style = MaterialTheme.typography.bodyLarge,
        color = Color.Black,
    )
}

@Composable
private fun SecondaryText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
        style = MaterialTheme.typography.bodySmall,
        color = Color.DarkGray,
    )
}

@Composable
private fun FileIcon(modifier: Modifier = Modifier, drawable: Painter) {
    Image(
        painter = drawable,
        contentDescription = "Тип файла",
        modifier = modifier.size(48.dp)
    )
}