package com.example.praktikum6.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum6.data.source.StudentRepository
import com.example.praktikum6.ui.detail.DetailViewModel

class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set
    fun loadStudents() {
        val students = repository.getStudents()
        uiState = if (students != null) {
            HomeUiState(students = students)
        } else {
            HomeUiState(errorMessage = "Tidak ada student")
        }
    }
    companion object {
        fun factory(repository: StudentRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(repository) as T
                }
            }
    }
}