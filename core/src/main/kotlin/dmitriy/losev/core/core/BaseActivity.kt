package ru.mpei.core.core

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.theme.FileExchangerTheme

abstract class BaseActivity : ComponentActivity() {

    abstract val baseViewModel: BaseViewModel

    @Composable
    abstract fun UI()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FileExchangerTheme {
                UI()
                val errorMessage by baseViewModel.errorMessage.collectAsState()
                val errorState by baseViewModel.errorState.collectAsState()

                ErrorDialog(
                    state = errorState,
                    errorMessage = errorMessage,
                    closeDialog = baseViewModel.close
                )

            }
        }
    }

    @Composable
    private fun ErrorDialog(state: Boolean, errorMessage: String, closeDialog: () -> Unit) {
        if (state) {
            AlertDialog(
                onDismissRequest = closeDialog,
                title = {
                    Text(text = "Ошибка!")
                },
                text = {
                    Text(text = errorMessage)
                },
                confirmButton = {
                    Button(onClick = closeDialog) {
                        Text(text = "ОК")
                    }
                }
            )
        }
    }
}

