package dmitriy.losev.filemanager.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed interface Screens {

    val name: String

    val route: String

    val arguments: List<NamedNavArgument>
        get() = emptyList()

    object PermissionScreen: Screens {

        override val name = "permission"

        override val route = name
    }
    object FileScreen: Screens {

        const val PATH = "path"

        override val name = "file"

        override val route = "$name/{$PATH}"

        override val arguments = listOf(
            navArgument(name = PATH) { type = NavType.StringType },
        )

        fun createRoute(path: String): String {
            return "$name/${path.replace(oldChar = '/', newChar = '+')}"
        }
    }
}
