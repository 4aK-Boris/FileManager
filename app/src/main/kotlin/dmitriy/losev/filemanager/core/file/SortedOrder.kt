package dmitriy.losev.filemanager.core.file

import dmitriy.losev.filemanager.domain.models.FileModel

enum class SortedOrder(val title: String, val comparator: Comparator<FileModel>) {
    ALPHABET(title = "По названию", comparator = compareBy { entityModel -> entityModel.name }),
    SIZE(title = "По размеру", comparator = compareBy { entityModel -> entityModel.sizeInBytes }),
    DATE(title = "По дате", comparator = compareBy { entityModel -> entityModel.dateInMillis }),
    EXTENSION(title = "По расширению", comparator = compareBy { entityModel -> entityModel.extension.type })
}