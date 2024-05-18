package com.example.goproapplication.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goproapplication.R
import com.example.goproapplication.model.Announcement
import com.example.goproapplication.model.AnnouncementViewModel
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import java.text.SimpleDateFormat
import java.util.TimeZone

@Composable
fun AnnouncementScreen(){
    val announcementViewModel: AnnouncementViewModel = viewModel()
    val announcements by announcementViewModel.announcements
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
                Text(
                    text = "Announcement",
                    fontSize = 30.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .fillMaxWidth(),
                    fontFamily = FontFamily(Font(R.font.title_font)),
                    textAlign = TextAlign.Center
                )
            }
            item {
                AnnouncementList(announcement = announcements, onDelete = { id ->
                    announcementViewModel.deleteAnnouncement(id)
                })
            }
        }

        FloatingActionButton(
            onClick = { GoProAppRoute.navigateTo(Screen.PostAnnouncementScreen) },
            modifier = Modifier
                .padding(vertical = 100.dp, horizontal = 20.dp)
                .align(Alignment.BottomEnd)
                .shadow(5.dp)
        ) {
            Icon(imageVector = Icons.Filled.Add, contentDescription = "Post Announcement")
        }
    }
}


@Composable
fun AnnouncementList(announcement: List<Announcement>, onDelete: (String) -> Unit) {
    Column(modifier = Modifier.padding(bottom = 100.dp)) {
        announcement.forEach { announcement ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 8.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp)
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.teacher),
                            contentDescription = "Teacher_Pic",
                            modifier = Modifier
                                .size(40.dp)
                                .clip(RoundedCornerShape(20.dp))
                        )

                        Column(
                            modifier = Modifier
                                .padding(start = 10.dp)
                        ) {
                            Text(
                                text = announcement.user.name ?: "",
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(Font(R.font.regular_font)),
                                fontSize = 20.sp
                            )
                            Text(
                                text = formatDate(announcement.creationTime),
                                fontWeight = FontWeight.Light,
                                fontFamily = FontFamily(Font(R.font.regular_font)),
                                fontSize = 15.sp
                            )
                        }
                        IconButton(
                            onClick = { onDelete(announcement.id) }) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete"

                            )
                        }
                    }
                    Column (
                        modifier = Modifier
                            .padding(horizontal = 15.dp, vertical = 10.dp)){
                        Text(
                            text = announcement.title,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.regular_font)),
                            modifier = Modifier
                                .padding(vertical = 5.dp)
                        )


                        Text(
                            text = announcement.content,
                            fontWeight = FontWeight.Light,
                            fontSize = 15.sp,
                            fontFamily = FontFamily(Font(R.font.regular_font))
                        )

                    }
                }
            }
        }
    }
}

@SuppressLint("SimpleDateFormat")
fun formatDate(creationTime: Long): String {
    val dateFormat = SimpleDateFormat("dd MMM yyyy")
    dateFormat.timeZone = TimeZone.getDefault()
    return dateFormat.format(creationTime)
}
