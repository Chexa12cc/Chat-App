package com.codewithcc.chatapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.codewithcc.chatapp.ui.theme.Background
import com.codewithcc.chatapp.ui.theme.Blue
import com.codewithcc.chatapp.ui.theme.ChatAppTheme
import com.codewithcc.chatapp.ui.theme.Pink
import com.codewithcc.chatapp.ui.theme.Scarlet

@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val viewModel = viewModel<ChatViewModel>()

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (viewModel.promptList.isNotEmpty() || viewModel.startChat) {
                BottomBar(
                    prompt = viewModel.prompt,
                    onPromptChange = {
                        viewModel.prompt = it
                    },
                    onRequest = {
                        viewModel.getResponse(it)
                    }
                )
            }
        }
    ) { values ->
        if (viewModel.promptList.isEmpty()) {
            WelcomeScreen(onSignIn = {})
        } else {
            viewModel.startChat = true
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = values,
                reverseLayout = true
            ) {
                items(viewModel.promptList.reversed()) {
                    ChatCard(data = it)
                }
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth(),
        color = MaterialTheme.colorScheme.primaryContainer
    ) {
        Row(
            modifier = Modifier.padding(vertical = 16.dp, horizontal = 18.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                modifier = Modifier.size(30.dp),
                painter = painterResource(id = R.drawable.gemini_icon),
                contentDescription = stringResource(id = R.string.gemini_icon)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = stringResource(id = R.string.gemini_chat),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    prompt: String,
    onPromptChange: (String) -> Unit,
    onRequest: (String) -> Unit
) {
    Surface(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = prompt,
                onValueChange = { onPromptChange(it) },
                placeholder = {
                    Text(text = "Ask anything to Gemini")
                }
            )
            Spacer(modifier = Modifier.width(12.dp))
            IconButton(enabled = prompt.isNotEmpty(), onClick = { onRequest(prompt) }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.Send,
                    contentDescription = stringResource(id = R.string.button_icon)
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ChatAppTheme {
        ChatScreen()
    }
}