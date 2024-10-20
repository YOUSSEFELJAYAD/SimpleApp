package com.simple.views.Componment

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kiwi.orbit.compose.ui.controls.Text
import kiwi.orbit.compose.ui.controls.TextField


@Composable
fun MessageInputField(navController: NavHostController , _sendMessage : (String) -> Unit) {
    val message = remember { mutableStateOf("") }
    TextField(
        value = message.value,
        onValueChange = {
            message.value = it
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .navigationBarsPadding()
            .imePadding()
       ,
        placeholder = {
            Text(
                text = "Type a message...",
            )
        },


        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                _sendMessage(message.value)
            }
        )
    )
}