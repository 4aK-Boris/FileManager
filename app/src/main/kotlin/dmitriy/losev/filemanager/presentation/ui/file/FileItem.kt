package dmitriy.losev.filemanager.presentation.ui.file

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import dmitriy.losev.filemanager.R
import dmitriy.losev.filemanager.domain.models.FileModel

@Composable
fun FileItem(
    fileItem: FileModel,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp).clickable { onClick() },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FileIcon(drawable = painterResource(id = fileItem.extension.idRes))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            MainText(text = fileItem.name)

            Row(
                modifier = Modifier.fillMaxWidth(),
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
        modifier = modifier.size(50.dp)
    )
}