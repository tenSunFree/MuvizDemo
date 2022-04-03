package com.example.muvizdemo.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.muvizdemo.R

@OptIn(ExperimentalMaterialApi::class)
@Destination
@Composable
fun LoginScreen(
) {
    ConstraintLayout(
        modifier = Modifier.fillMaxSize()
    ) {
        val (background) = createRefs()
        Image(
            painterResource(
                id = R.drawable.icon_login
            ),
            contentDescription = "background",
            modifier = Modifier
                .constrainAs(background) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}