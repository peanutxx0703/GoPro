package com.example.goproapplication

sealed class Screen(val route: String) {
    object TeacherCourseViewScreen : Screen("teacher_course_view")
    object TeacherDashboardScreen : Screen("teacher_dashboard")
    object AnnouncementScreen : Screen("announcement")
    object TeacherProfileScreen : Screen("teacher_profile")
}