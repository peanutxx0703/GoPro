package com.example.goproapplication.ui.theme

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goproapplication.R
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.UUID

@Composable
fun PostTask() {
    val taskTitleState = remember { mutableStateOf(TextFieldValue()) }
    val taskContentState = remember { mutableStateOf(TextFieldValue()) }
    val taskContext = LocalContext.current

    val db = Firebase.firestore
    val todotaskCollection = db.collection("todotask")

    Image(
        painter = painterResource(id = R.drawable.gopro_background2),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                onClick = { GoProAppRoute.navigateTo(Screen.TaskScreen) }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            HeadingTextComponent(value = "Add To-Do")
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {

        }
        TextField(
            value = taskTitleState.value,
            onValueChange = { taskTitleState.value = it },
            placeholder = { Text(text = "Task Title", color = Color.White) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                fontFamily = FontFamily(Font(R.font.regular_font))
            ),
            modifier = Modifier
                .height(50.dp)
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
        )

        TextField(
            value = taskContentState.value,
            onValueChange = { taskContentState.value = it },
            placeholder = { Text(text = "Task details :", color = Color.White) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            singleLine = false,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = Color.Transparent,
                unfocusedLabelColor = Color.Transparent,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            ),
            textStyle = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White
            ),
            modifier = Modifier
                .padding(10.dp)
                .height(150.dp)
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
        )
        Button(
            onClick = {

                val title = taskTitleState.value.text
                val content = taskContentState.value.text
                val creationTime = System.currentTimeMillis()
                val id = UUID.randomUUID().toString()

                val taskData = hashMapOf(
                    "id" to id,
                    "title" to title,
                    "content" to content,
                    "creation_time" to creationTime,
                )

                todotaskCollection.document(id).set(taskData)
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot added with ID: $id")
                        // Show a success message
                        showToast(taskContext, "Announcement posted")

                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                        // Show an error message
                        showToast(taskContext, "Failed to post task")
                    }
            },
            modifier = Modifier
                .fillMaxWidth()
                .size(30.dp, 50.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(40.dp)
                    .background(
                        brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Cyan)),
                        shape = RoundedCornerShape(40.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Post",
                    fontSize = 20.sp,
                    fontFamily = FontFamily(Font(R.font.regular_font))
                )
            }
        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.TaskScreen)
    }
}
