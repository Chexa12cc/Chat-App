package com.codewithcc.chatapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch
import java.util.UUID

class ChatViewModel: ViewModel() {

    var prompt by mutableStateOf("")
    val promptList by lazy {
        mutableStateListOf<ChatData>()
    }
    var startChat by mutableStateOf(false)

    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_KEY
    )

    fun getResponse(prompt: String) {
        this.prompt = ""
        promptList.add(
            ChatData(
                UUID.randomUUID().toString(),
                prompt,
                "user"
            )
        )
        viewModelScope.launch {
            promptList.add(
                ChatData(
                    UUID.randomUUID().toString(),
                    "Typing...",
                    "model"
                )
            )
            val chat = generativeModel.startChat(
                promptList.map {
                    content(it.role) {
                        text(it.prompt)
                    }
                }
            )
            val response = chat.sendMessage(prompt)
            promptList.removeLast()
            promptList.add(
                ChatData(
                    UUID.randomUUID().toString(),
                    response.text!!,
                    "model"
                )
            )
        }
    }

}