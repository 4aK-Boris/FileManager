package dmitriy.losev.filemanager.domain.models

import dmitriy.losev.filemanager.core.file.FileExtension
import java.nio.file.attribute.FileTime

data class FileModel(
   val extension: FileExtension,
   val path: String,
   val name: String,
   val size: String,
   val isChanged: Boolean,
   val dateOfCreation: String,
   val sizeInBytes: Long,
   val dateInMillis: Long,
)