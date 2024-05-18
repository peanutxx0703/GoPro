package com.example.goproapplication.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.goproapplication.R
import com.example.goproapplication.components.HeadingTextComponent
import com.example.goproapplication.model.Task
import com.example.goproapplication.model.TaskViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.navigation.SystemBackButtonHandler
import java.text.SimpleDateFormat
import java.util.TimeZone


@Composable
fun TaskScreen(){
    val taskViewModel: TaskViewModel = viewModel()
    val tasks by taskViewModel.tasks
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.gopro_background2),
            contentDescription = "background",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn {
            item {
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
                    HeadingTextComponent(value = "To-Do List")
                }
            }
            item {
                TaskList(task = tasks, onDelete = { id ->
                    taskViewModel.deleteTask(id)
                })
            }
        }

        FloatingActionButton(
            onClick = { GoProAppRoute.navigateTo(Screen.PostTask) },
            modifier = Modifier
                .padding(vertical = 100.dp, horizontal = 20.dp)
                .align(Alignment.BottomEnd)
                .shadow(5.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Post Task")
        }
    }
    SystemBackButtonHandler {
        GoProAppRoute.navigateTo(Screen.StudentDashboardScreen)
    }
}


@Composable
fun TaskList(task: List<Task>, onDelete: (String) -> Unit) {
    Column(modifier = Modifier.padding(bottom = 100.dp)) {
        task.forEach { task ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 22.dp, vertical = 7.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            Text(
                                text = formatDate(task.creationTime),
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(Font(R.font.regular_font)),
                                fontSize = 18.sp,
                                modifier = Modifier.padding(end = 130.dp).padding(11.dp)

                            )
                        }
                        IconButton(
                            onClick = { onDelete(task.id) }) {
                            Icon(

                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"

                            )
                        }
                    }
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp))
                    {
                        Text(
                            text = task.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.regular_font)),
                            modifier = Modifier
                                .padding(vertical = 3.dp).padding(2.dp).padding(horizontal = 20.dp)
                        )


                        Text(
                            text = task.content,
                            fontWeight = FontWeight.Light,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.regular_font)),
                            modifier = Modifier
                                .padding(vertical = 3.dp).padding(2.dp).padding(horizontal = 20.dp)
                        )

                    }
                }
            }
        }
    }
}