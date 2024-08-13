package com.codewithcc.chatapp

import android.graphics.Bitmap

data class ChatData(
    val id: String,
    val prompt: String,
    val role: String,
    val bitmap: Bitmap? = null,
)
