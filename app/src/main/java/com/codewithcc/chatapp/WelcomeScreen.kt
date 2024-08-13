package com.codewithcc.chatapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.codewithcc.chatapp.ui.theme.Background
import com.codewithcc.chatapp.ui.theme.Blue
import com.codewithcc.chatapp.ui.theme.Pink
import com.codewithcc.chatapp.ui.theme.Scarlet

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onSignIn: () -> Unit) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(80.dp).background(Color.White, CircleShape).padding(16.dp),
            painter = painterResource(id = R.drawable.app_logo),
            contentDescription = stringResource(id = R.string.gemini_icon)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.welcome_gemini_text),
            fontSize = 40.sp,
            fontWeight = FontWeight.ExtraBold,
            textAlign = TextAlign.Justify,
            style = TextStyle(
                brush = Brush.horizontalGradient(
                    listOf(Blue, Pink, Scarlet)
                )
            )
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {  }) {
            Text(text = "Sign In to Start Chat", fontSize = 18.sp)
        }
    }
}