package com.example.goproapplication.ui.theme

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goproapplication.R
import com.example.goproapplication.model.AnnouncementViewModel
import com.example.goproapplication.model.User
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import java.util.UUID

private const val TAG = "PostAnnouncement"


fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

@Composable
fun PostAnnouncement() {
    val titleState = remember { mutableStateOf(TextFieldValue()) }
    val contentState = remember { mutableStateOf(TextFieldValue()) }
    val context = LocalContext.current

    val announcementViewModel: AnnouncementViewModel = viewModel()
    val currentUser = announcementViewModel.currentUser ?: "Unknown User"

    val db = Firebase.firestore
    val announcementsCollection = db.collection("announcement")

    Image(
        painter = painterResource(id = R.drawable.gopro_background2),
        contentDescription = "gopro_background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "New Announcement",
            color = Color.White,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.title_font)),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.teacher),
                contentDescription = "Teacher_Pic",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(20.dp))
            )
            Text(
                text = currentUser,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                modifier = Modifier.padding(10.dp),
                fontFamily = FontFamily(Font(R.font.regular_font)),
                color = Color.White
            )
        }
        TextField(
            value = titleState.value,
            onValueChange = { titleState.value = it },
            placeholder = { Text(text = "Title", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default,
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
                .padding(10.dp)
                .height(50.dp)
                .border(1.dp, Color.White, shape = RoundedCornerShape(8.dp))
        )

        TextField(
            value = contentState.value,
            onValueChange = { contentState.value = it },
            placeholder = { Text(text = "Share something...", color = Color.White) },
            keyboardOptions = KeyboardOptions.Default,
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
                val title = titleState.value.text
                val content = contentState.value.text
                val creationTime = System.currentTimeMillis()
                val user = User(
                    currentUser
                )
                val id = UUID.randomUUID().toString()

                val announcement = hashMapOf(
                    "id" to id,
                    "title" to title,
                    "content" to content,
                    "creation_time" to creationTime,
                    "user" to user
                )

                announcementsCollection.document(id).set(announcement)
                    .addOnSuccessListener {
                        Log.d(TAG, "DocumentSnapshot added with ID: $id")
                        // Show a success message
                        showToast(context, "Announcement posted")
                        GoProAppRoute.navigateTo(Screen.AnnouncementScreen)
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                        // Show an error message
                        showToast(context, "Failed to post announcement")
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

}