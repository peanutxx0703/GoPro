package com.example.goproapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.screens.WelcomeScreen
import com.example.goproapplication.screens.WelcomeScreenPreview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
         GoPro()
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    GoProApp()
}

