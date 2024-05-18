package com.example.goproapplication.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen

@Composable
fun StudentDashboardScreen() {
    Image(
        painter = painterResource(id = R.drawable.gopro_background2),
        contentDescription = "background",
        contentScale = ContentScale.FillBounds,
        modifier = Modifier.fillMaxSize()
    )

    val itemList = listOf("Student Registration", "Student Score", "Tuition Fee", "Student Survey")
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Dashboard",
            fontSize = 40.sp,
            color = Color.White,
            fontFamily = FontFamily(Font(R.font.title_font)),
            modifier = Modifier.padding(15.dp)
        )
        Button(
            onClick = { GoProAppRoute.navigateTo(Screen.QuizAchievement) },
            modifier = Modifier
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(120.dp, 50.dp)
                .background(
                    Color.Transparent
                )

                .border(1.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Course Achievement",
                    fontFamily = FontFamily(Font(R.font.regular_font)))
            }

        }
        Button(
            onClick = { GoProAppRoute.navigateTo(Screen.TaskScreen) },
            modifier = Modifier
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(120.dp, 50.dp)
                .background(
                    Color.Transparent
                )
                .border(1.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(text = "ToDo List",
                    fontFamily = FontFamily(Font(R.font.regular_font)))
            }

        }
        Button(
            onClick = { GoProAppRoute.navigateTo(Screen.GenerateTuitionFeeMonthlyScreen) },
            modifier = Modifier
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(120.dp, 50.dp)
                .background(
                    Color.Transparent
                )
                .border(1.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Tuition Fee",
                    fontFamily = FontFamily(Font(R.font.regular_font)))
            }

        }

        Button(
            onClick = { GoProAppRoute.navigateTo(Screen.Survey) },
            modifier = Modifier
                .padding(8.dp),
            colors = ButtonDefaults.buttonColors(Color.Transparent)

        ) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .size(120.dp, 50.dp)
                .background(
                    Color.Transparent
                )
                .border(1.dp, Color.White, CircleShape),
                contentAlignment = Alignment.Center
            ){
                Text(text = "Student Survey",
                    fontFamily = FontFamily(Font(R.font.regular_font)))
            }

        }

    }
}