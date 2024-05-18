package com.example.goproapplication

import ProfileViewModel
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.goproapplication.data.login.StudentLoginViewModel
import com.example.goproapplication.data.login.TeacherLoginViewModel
import com.example.goproapplication.data.signup.SignUpViewModel
import com.example.goproapplication.model.BottomNavigationItem
import com.example.goproapplication.navigation.GoProAppRoute
import com.example.goproapplication.navigation.Screen
import com.example.goproapplication.screens.ForgotPasswordScreen
import com.example.goproapplication.screens.WelcomeScreen
import com.example.goproapplication.student.login.StudentLoginScreen
import com.example.goproapplication.student.profile.StudentProfileScreen
import com.example.goproapplication.student.settings.StudentChangePasswordScreen
import com.example.goproapplication.student.settings.StudentContactUsScreen
import com.example.goproapplication.student.settings.StudentPrivacyPolicyScreen
import com.example.goproapplication.student.settings.StudentSettingsScreen
import com.example.goproapplication.student.settings.StudentTermsAndConditionsScreen
import com.example.goproapplication.student.signup.StudentSignUpScreen
import com.example.goproapplication.teacher.login.TeacherLoginScreen
import com.example.goproapplication.teacher.profile.TeacherProfileScreen
import com.example.goproapplication.teacher.settings.TeacherChangePasswordScreen
import com.example.goproapplication.teacher.settings.TeacherContactUsScreen
import com.example.goproapplication.teacher.settings.TeacherPrivacyPolicyScreen
import com.example.goproapplication.teacher.settings.TeacherSettingsScreen
import com.example.goproapplication.teacher.settings.TeacherTermsAndConditionsScreen
import com.example.goproapplication.teacher.signup.TeacherSignUpScreen
import com.example.goproapplication.ui.theme.AnnouncementScreen
import com.example.goproapplication.ui.theme.GenerateTuitionFeeMonthly
import com.example.goproapplication.ui.theme.PostAnnouncement
import com.example.goproapplication.ui.theme.StudentAnnouncementScreen
import com.example.goproapplication.ui.theme.StudentDashboardScreen
import com.example.goproapplication.ui.theme.TeacherDashboardScreen

val items = listOf(
    BottomNavigationItem(
        title="Home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomNavigationItem(
        title="Dashboard",
        selectedIcon = Icons.Filled.Face,
        unselectedIcon = Icons.Outlined.Face
    ),
    BottomNavigationItem(
        title="Announcement",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications
    ),
    BottomNavigationItem(
        title="Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle
    ),
)

@Composable
fun TeacherNav(){
    var bottomNavState by rememberSaveable {
        mutableStateOf(0)
    }
    Scaffold(
        bottomBar = {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier) {
                    GoProApp()
                }
                // Bottom Navigation Bar
                NavigationBar(
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .align(Alignment.BottomCenter)
                ) {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = bottomNavState == index,
                            onClick = {
                                bottomNavState = index
                                when (index) {
                                    0 -> GoProAppRoute.navigateTo(Screen.TeacherCourseViewScreen)
                                    1 -> GoProAppRoute.navigateTo(Screen.TeacherDashboardScreen)
                                    2 -> GoProAppRoute.navigateTo(Screen.AnnouncementScreen)
                                    3 -> GoProAppRoute.navigateTo(Screen.TeacherProfileScreen)
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if (bottomNavState == index) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title,
                                    fontFamily = FontFamily(Font(R.font.regular_font)),
                                    fontSize = 12.sp)
                            }
                        )
                    }
                }
            }
        }
    ) { contentPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
        ) {
            when (bottomNavState) {
                0 -> GoProAppRoute.navigateTo(Screen.TeacherCourseViewScreen)
                1 -> GoProAppRoute.navigateTo(Screen.TeacherDashboardScreen)
                2 -> GoProAppRoute.navigateTo(Screen.AnnouncementScreen)
                3 -> GoProAppRoute.navigateTo(Screen.TeacherProfileScreen)
            }
            }
        }

    }


@Preview
@Composable
fun TeacherNavBar(){
    TeacherNav()
}