package com.naeemdev.jetpackcompose.multiscreenresizeablapp.ui.composables

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "phone", device = Devices.PHONE)
@Preview(name = "tablet", device = Devices.TABLET)
@Preview(name = "foldable", device = Devices.FOLDABLE)
@Preview(name = "custom", device = "spec:width=1280dp,height=800dp,dpi=480")
@Preview(name = "desktop medium", device = "id:desktop_medium")
annotation class ReferenceDevices
