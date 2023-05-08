package dmitriy.losev.filemanager.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dmitriy.losev.database.domain.models.RoomFileModel
import dmitriy.losev.database.domain.usecases.RoomFileUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun TestScreen() {

    val roomFileUseCases = koinInject<RoomFileUseCases>()

    val scope = rememberCoroutineScope()

    val roomFileModel = RoomFileModel(path = "path", hash = byteArrayOf(1, 2, 3))

    var value by remember { mutableStateOf(RoomFileModel(path = "", hash = byteArrayOf())) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                roomFileUseCases.addFile(roomFileModel)
            }
        }) {
            Text(text = "Вставить файл")
        }

        Button(onClick = {
            scope.launch(Dispatchers.IO) {
                value = roomFileUseCases.getFile(roomFileModel.path)
            }
        }) {
            Text(text = "Получить файл")
        }
        
        Text(text = value.toString())
    }
}