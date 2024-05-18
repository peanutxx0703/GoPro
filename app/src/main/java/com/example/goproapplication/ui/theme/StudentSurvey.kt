package com.example.goproapplication.ui.theme

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.goproapplication.R
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Survey() {
    var name by remember { mutableStateOf(TextFieldValue()) }
    var teacherFeedback by remember { mutableStateOf(TextFieldValue()) }
    var appFeedback by remember { mutableStateOf(TextFieldValue()) }
    var complain by remember { mutableStateOf(TextFieldValue()) }
    val titleFont = FontFamily(Font(R.font.title_font))
    val regularFont = FontFamily(Font(R.font.regular_font))

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Success") },
            text = { Text("Submitted Successfully") },
            confirmButton = {
                Button(
                    onClick = { showDialog = false }
                ) {
                    Text("OK")
                }
            }
        )
    }

    Box(modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gopro_background2),
            contentDescription = "gopro_background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = { GoProAppRoute.navigateTo(Screen.StudentDashboardScreen) }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                HeadingTextComponent(value = "Survey Form")
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name :", fontFamily = regularFont) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    ),
                modifier = Modifier
                    .padding(vertical = 18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )

            OutlinedTextField(
                value = teacherFeedback,
                onValueChange = { teacherFeedback = it },
                label = { Text("Any words to teacher :", fontFamily = regularFont) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    ),
                modifier = Modifier
                    .padding(vertical = 18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )

            OutlinedTextField(
                value = appFeedback,
                onValueChange = { appFeedback = it },
                label = { Text("Do you like this app :", fontFamily = regularFont) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    ),
                modifier = Modifier
                    .padding(vertical = 18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )

            OutlinedTextField(
                value = complain,
                onValueChange = { complain = it },
                label = { Text("Any complain :", fontFamily = regularFont) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedLabelColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,

                    ),
                modifier = Modifier
                    .padding(vertical = 18.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = null
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { saveData(name.text, teacherFeedback.text, appFeedback.text, complain.text){
                    showDialog = true
                }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 66.dp)
            ) {
                Text("Submit")
            }
        }
    }

    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.StudentDashboardScreen)
    }
}

fun saveData(name: String,
             teacherFeedback: String,
             appFeedback: String,
             complain: String,
             onSuccess: () -> Unit)
{
    val db = Firebase.firestore
    val surveyCollection = db.collection("survey")

    surveyCollection.get()
        .addOnSuccessListener { documents ->

            if (name.isNotEmpty() && teacherFeedback.isNotEmpty() && appFeedback.isNotEmpty() && complain.isNotEmpty()) {
                val surveyData = hashMapOf(
                    "name" to name,
                    "teacherFeedback" to teacherFeedback,
                    "appFeedback" to appFeedback,
                    "complain" to complain
                )
                surveyCollection.add(surveyData)
                    .addOnSuccessListener { documentReference ->
                        Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error adding document", e)
                    }
            } else {
                Log.e(TAG, "One or more input fields are empty. Quiz data not saved.")
            }
        }
        .addOnFailureListener { exception ->
            Log.e(TAG, "Error getting documents: ", exception)
        }
}
