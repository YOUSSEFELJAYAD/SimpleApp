package com.simple.views


import android.annotation.SuppressLint
import androidx.activity.compose.ReportDrawn

import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.rounded.BrightnessMedium
import androidx.compose.material.icons.rounded.Call

import androidx.compose.material.icons.rounded.Verified

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset

import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInWindow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.center
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toOffset
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.simple.di.ProviderComposable.GProvider
import com.kiwi.navigationcompose.typed.navigate
import com.simple.data.model.userByPhone
import com.simple.navigation.NavigationConfig
import kiwi.orbit.compose.ui.OrbitTheme
import kiwi.orbit.compose.ui.controls.Icon
import kiwi.orbit.compose.ui.controls.IconButton
import kiwi.orbit.compose.ui.controls.Scaffold
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TopAppBar
import kiwi.orbit.compose.ui.utils.plus
import kotlinx.serialization.ExperimentalSerializationApi
import androidx.compose.material.icons.Icons.Rounded as MIcons




@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalSerializationApi
@Composable
internal fun MainScreen(
    navController: NavHostController,
    onThemeToggle: (Offset) -> Unit,
) {

    val gProvider: GProvider = hiltViewModel()

    //navController.navigate(NavigationConfig.AiTeam)
    //val scrollBehavior = TopAppBarScrollBehavior.enterAlways()

    Scaffold(
        modifier = Modifier.testTag(""),
        topBar = {
            TopAppBar(
                title = {
                   /* Image(
                        painter = painterResource(R.drawable.logo_name),
                        modifier = Modifier.height(59.dp),
                        contentDescription = null
                    )*/

                    Text(
                        text = "Opulent Messenger",
                    )
                },
                actions = {
                    var offset by remember { mutableStateOf(Offset(0f, 0f)) }

                    IconButton(
                        modifier = Modifier.onGloballyPositioned {
                            offset = it.positionInWindow() + it.size.center.toOffset()
                        },
                        onClick = { onThemeToggle(offset) },
                    ) {
                        Icon(MIcons.Verified, contentDescription = null)
                    }
                    IconButton(
                        modifier = Modifier.onGloballyPositioned {
                            offset = it.positionInWindow() + it.size.center.toOffset()
                        },
                        onClick = { navController.navigate(NavigationConfig.SettingView) },
                    ) {
                        Icon(MIcons.Call, contentDescription = null)
                    }
                    IconButton(
                        modifier = Modifier.onGloballyPositioned {
                            offset = it.positionInWindow() + it.size.center.toOffset()
                        },
                        onClick = { onThemeToggle(offset) },
                    ) {
                        Icon(MIcons.BrightnessMedium, contentDescription = null)
                    }
                },

            )
        },
        backgroundColor = OrbitTheme.colors.surface.subtle,
    ) { contentPadding ->
        LazyVerticalGrid(
            columns = GridCells.Adaptive(160.dp),
            contentPadding = contentPadding + PaddingValues(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp,
                bottom = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {

            item(span = { GridItemSpan(maxLineSpan) }) {
                Text(
                    text = "Developed with love in Morocco by Y-JD, @2024",
                    style = OrbitTheme.typography.bodySmall,
                    textAlign = TextAlign.Center
                )
            }
            item(span = { GridItemSpan(maxLineSpan) }) {
                Spacer(
                    modifier = Modifier
                        .imePadding()
                        .padding(15.dp)
                )
            }
        }
    }.let {

    }
    ReportDrawn()
}

