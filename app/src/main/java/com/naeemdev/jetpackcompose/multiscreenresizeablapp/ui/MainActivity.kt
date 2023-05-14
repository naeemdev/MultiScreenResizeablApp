package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables.NoteApp
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.theme.MultiScreenResizeablAppTheme
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.DevicePosture
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.isBookPosture
import com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.util.isSeparating
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class MainActivity : ComponentActivity() {
    private val notesViewModel: NotesViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val devicePostureFlow = WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DevicePosture.BookPosture(foldingFeature.bounds)

                    isSeparating(foldingFeature) ->
                        DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

                    else -> DevicePosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )

        setContent {
            MultiScreenResizeablAppTheme {
                // A surface container using the 'background' color from the theme
                val windowSizeClass = calculateWindowSizeClass(activity = this)
                val devicePosture = devicePostureFlow.collectAsState().value
                NoteApp(
                    windowSizeClass = windowSizeClass.widthSizeClass,
                    devicePosture = devicePosture,
                    notesViewModel = notesViewModel
                )
            }
        }
    }
}
