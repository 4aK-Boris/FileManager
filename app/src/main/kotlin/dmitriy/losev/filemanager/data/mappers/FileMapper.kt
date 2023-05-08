package dmitriy.losev.filemanager.data.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import dmitriy.losev.filemanager.core.file.FileExtension
import dmitriy.losev.filemanager.data.dto.FileDTO
import dmitriy.losev.filemanager.domain.models.FileModel
import java.io.File
import java.nio.file.Files
import java.nio.file.attribute.BasicFileAttributes
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FileMapper {

    @RequiresApi(Build.VERSION_CODES.O)
    fun map(value: FileDTO): FileModel {
        val attributes = Files.readAttributes(value.file.toPath(), BasicFileAttributes::class.java)
        val fileName = value.file.name
        val date = Date(attributes.creationTime().toMillis())
        return FileModel(
            path = value.file.absolutePath,
            name = fileName,
            sizeInBytes = value.file.length(),
            size = getSize(file = value.file),
            isChanged = false,
            extension = FileExtension.getExtension(file = value.file),
            dateOfCreation = formatter.format(date),
            dateInMillis = attributes.creationTime().toMillis()
        )
    }

    fun map(value: FileModel): FileDTO {
        return FileDTO(File(value.path))
    }
    private fun getSize(file: File):String {
        return if (file.isDirectory) {
            getFolderSize(count = file.listFiles()?.size?: 0)
        } else {
            getFileSize(size = file.length())
        }
    }

    private fun getFileSize(size: Long): String {
        var (k, l) = size to 0
        while (k >= 1000) {
            k /= 1000
            l++
        }
        return when (l) {
            0 -> "$k Байт"
            1 -> "$k Кб"
            2 -> "$k Мб"
            3 -> "$k Гб"
            else -> "$k Тб"
        }
    }

    private fun getFolderSize(count: Int): String {
        return when {
            count == 1 -> "$count файл"
            count in 2..4 -> "$count файла"
            count in 5..20 -> "$count файлов"
            count % 10 == 1 -> "$count файл"
            count % 10 in 2..4 -> "$count файла"
            else -> "$count файлов"
        }
    }

    companion object {
        private val formatter = SimpleDateFormat("dd.MM.YYYY", Locale.ENGLISH)
    }
}