package dmitriy.losev.filemanager.presentation.ui.file

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dmitriy.losev.core.core.theme.SurfaceLight
import dmitriy.losev.filemanager.R
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(viewModel: FileViewModel) {

    val sortedOrder by viewModel.sortedOrder.collectAsState()
    val sortedType by viewModel.sortedType.collectAsState()

    val (menuState, onMenuStateChanged) = rememberSaveable {
        mutableStateOf(value = false)
    }

    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = SurfaceLight),
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = stringResource(id = R.string.title),
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Cursive,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.W700
                )

                Box {

                    IconButton(onClick = { onMenuStateChanged(true) }) {
                        Icon(
                            imageVector = Icons.Filled.Sort,
                            contentDescription = "Сортировка",
                            tint = Color.DarkGray
                        )
                    }

                    DropdownMenu(
                        expanded = menuState,
                        onDismissRequest = { onMenuStateChanged(false) }
                    ) {
                        SortedOrder.values().forEach { order ->
                            DropdownMenuItem(
                                text = { Text(text = order.title) },
                                onClick = {
                                    viewModel.onSortedOrderChanged(sortedOrder = order)
                                },
                                trailingIcon = {
                                    if (sortedOrder == order) {

                                        val angle =
                                            if (sortedType == SortedType.STRAIGHT) 0f else 180f

                                        val animatedAngle by animateFloatAsState(
                                            targetValue = angle,
                                            animationSpec = spring(),
                                            label = "Анимация поворота стрелки"
                                        )
                                        Icon(
                                            imageVector = Icons.Filled.ArrowUpward,
                                            contentDescription = "Сортировка в нужном порядке",
                                            modifier = Modifier.rotate(degrees = animatedAngle)
                                        )
                                    }
                                }
                            )
                        }
                    }

                }
            }

        }
    )
}