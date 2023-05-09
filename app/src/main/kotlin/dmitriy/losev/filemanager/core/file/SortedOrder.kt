package dmitriy.losev.filemanager.core.file

import androidx.annotation.StringRes
import dmitriy.losev.filemanager.R
import dmitriy.losev.filemanager.domain.models.FileModel

enum class SortedOrder(@StringRes val title: Int, val comparator: Comparator<FileModel>) {
    ALPHABET(title = R.string.order_alphabet, comparator = compareBy { entityModel -> entityModel.name }),
    SIZE(title = R.string.order_size, comparator = compareBy { entityModel -> entityModel.sizeInBytes }),
    DATE(title = R.string.order_date, comparator = compareBy { entityModel -> entityModel.dateInMillis }),
    EXTENSION(title = R.string.order_extension, comparator = compareBy { entityModel -> entityModel.extension.type })
}