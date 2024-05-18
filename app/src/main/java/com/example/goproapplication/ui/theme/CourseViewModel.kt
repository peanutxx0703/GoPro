package com.example.goproapplication.ui.theme

import com.example.goproapplication.data.CourseUiState

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CourseViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(CourseUiState())
    val uiState: StateFlow<CourseUiState> = _uiState.asStateFlow()

    fun setCourse(@StringRes course: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                course = course
            )
        }
    }

    fun setCourseImg(@DrawableRes courseImg: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                courseImg = courseImg
            )
        }
    }

    fun setQuizResult(quizResult: Int) {
        _uiState.update { currentState ->
            currentState.copy(
                quizResult = quizResult
            )
        }
    }

    fun setQuizName(quizName: String) {
        _uiState.update { currentState ->
            currentState.copy(
                quizName = quizName
            )
        }
    }

    fun setLessonNum(lessonNum: String) {
        _uiState.update { currentState ->
            currentState.copy(
                lessonNum = lessonNum
            )
        }
    }

    fun resetQuiz() {
        _uiState.update { currentState ->
            currentState.copy(
                course = 0,
                courseImg = 0,
                quizResult = 0
            )
        }
    }
}