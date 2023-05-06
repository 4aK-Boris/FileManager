package dmitriy.losev.core.core.exception

open class BaseException(val extraErrorCode: Int, override val message: String? = null) :
    Exception(message)

