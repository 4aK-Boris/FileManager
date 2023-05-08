package dmitriy.losev.filemanager.core.file

import dmitriy.losev.filemanager.domain.models.FileModel

enum class SortedType {
    STRAIGHT {
        override fun getComparator(comparator: Comparator<FileModel>): Comparator<FileModel> {
            return comparator
        }
    }, REVERSE {
        override fun getComparator(comparator: Comparator<FileModel>): Comparator<FileModel> {
            return comparator.reversed()
        }
    };

    abstract fun getComparator(comparator: Comparator<FileModel>):  Comparator<FileModel>

    companion object {
        fun reverseType(sortedType: SortedType): SortedType {
            return if (sortedType == STRAIGHT) {
                REVERSE
            } else {
                STRAIGHT
            }
        }
    }
}