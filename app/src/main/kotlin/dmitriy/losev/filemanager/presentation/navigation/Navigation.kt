package dmitriy.losev.filemanager.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dmitriy.losev.filemanager.presentation.ui.file.FileScreen
import dmitriy.losev.filemanager.presentation.ui.permission.PermissionScreen
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    viewModel: FileViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screens.PermissionScreen.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        
        composable(
            route = Screens.PermissionScreen.route,
        ) { _ -> 
            PermissionScreen(navController = navController)
        }
        
        composable(
            route = Screens.FileScreen.route,
            arguments = Screens.FileScreen.arguments
        ) { backStackEntry ->
            val path = backStackEntry.arguments?.getString(Screens.FileScreen.PATH)
            requireNotNull(path) { "path parameter wasn't found. Please make sure it's set!" }
            FileScreen(
                navController = navController,
                viewModel = viewModel,
                path = path.replace(oldChar = '+', newChar = '/')
            )
        }
    }
}
