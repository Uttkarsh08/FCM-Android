package com.uttkarsh.fcmpractice.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uttkarsh.fcmpractice.util.MessageState
import com.uttkarsh.fcmpractice.presentation.viewmodels.MessageViewModel

@Composable
fun MessageScreen(){

    val viewModel: MessageViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    val title = viewModel.title
    val description = viewModel.description

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when(state){
            is MessageState.Error -> {
                val error = (state as MessageState.Error).message
                Text(text = error)
                Button(
                    onClick = {
                        viewModel.sendMessage()
                    }
                ) {
                    Text(text = "Retry")
                }
            }
            MessageState.Idle -> {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = {
                        viewModel.onTitleChanged(it)
                    },
                    label = {Text(text = "Title")}
                )

                OutlinedTextField(
                    value = description.value,
                    onValueChange = {
                        viewModel.onDescriptionChange(it)
                    },
                    label = {Text(text = "Description")}
                )


                Button(
                    onClick = {
                        viewModel.sendMessage()
                    }
                ) {
                    Text(text = "Send Message")
                }
            }
            MessageState.Loading -> {
                CircularProgressIndicator()
            }
            MessageState.success -> {
                OutlinedTextField(
                    value = viewModel.title.value,
                    onValueChange = {
                        viewModel.onTitleChanged(it)
                    },
                    label = {Text(text = "Title")}
                )

                OutlinedTextField(
                    value = viewModel.description.value,
                    onValueChange = {
                        viewModel.onDescriptionChange(it)
                    },
                    label = {Text(text = "Title")}
                )


                Button(
                    onClick = {
                        viewModel.sendMessage()
                    }
                ) {
                    Text(text = "Send Message")
                }
                Text(text = "Message Sent Successfully")
            }
        }

    }
}