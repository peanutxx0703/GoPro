package com.example.goproapplication.ui.theme

import androidx.compose.runtime.Composable
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goproapplication.R
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class Quiz(
    var quizName: String = "",
    var quizResult: String = ""
)


@Composable
fun QuizHistory() {
    var quizzes by remember { mutableStateOf<List<Quiz>>(emptyList()) }

    LaunchedEffect(Unit) {
        fetchQuizzes { fetchedQuizzes ->
            quizzes = fetchedQuizzes
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gopro_background2),
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0x80000000))
                .padding(20.dp),
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
                HeadingTextComponent(value = "Quiz History")
            }
        }
    }
    // UI that uses quizzes
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 80.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(quizzes) { quiz ->
            quizAchievement(quiz)
        }
    }
}

fun fetchQuizzes(onQuizzesFetched: (List<Quiz>) -> Unit) {
    val db = Firebase.firestore
    db.collection("result")
        .get()
        .addOnSuccessListener { documents ->
            val quizList = mutableListOf<Quiz>()
            for (document in documents) {
                val name = document.getString("quizName")
                if (name != null) {
                    quizList.add(Quiz(quizName = name))
                }
            }
            onQuizzesFetched(quizList)
        }
        .addOnFailureListener { exception ->
            Log.w("QuizScreen", "Error getting documents: ", exception)
        }
}

@Composable
fun quizAchievement(quiz: Quiz) {
    val titleFont = FontFamily(Font(R.font.title_font))

    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = quiz.quizName,
                color = MaterialTheme.colorScheme.onSurface,
                fontFamily = titleFont,
                fontSize = 18.sp
            )
        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.StudentDashboardScreen)
    }
}

