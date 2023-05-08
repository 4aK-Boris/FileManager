package dmitriy.losev.filemanager.presentation.ui.permission

import android.Manifest
import android.os.Environment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import dmitriy.losev.filemanager.R
import dmitriy.losev.filemanager.presentation.navigation.Screens

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(navController: NavController) {

    val filesPermissionState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    )

    if (filesPermissionState.allPermissionsGranted) {
        val path = Environment.getExternalStorageDirectory().absolutePath
        navController.navigate(Screens.FileScreen.createRoute(path = path))
        navController.clearBackStack(Screens.PermissionScreen.route)
    } else {
        Permission(filesPermissionState = filesPermissionState)
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun Permission(filesPermissionState: MultiplePermissionsState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val text = if (filesPermissionState.shouldShowRationale) {
            stringResource(id = R.string.permission_1)
        } else {
            stringResource(id = R.string.permission_2)
        }

        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 32.dp),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.W400,
            fontFamily = FontFamily.SansSerif
        )

        Button(onClick = { filesPermissionState.launchMultiplePermissionRequest() }) {
            Text(text = stringResource(id = R.string.permission_button))
        }
    }
}