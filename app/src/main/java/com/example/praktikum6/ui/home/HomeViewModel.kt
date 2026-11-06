package com.example.praktikum6.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.praktikum6.data.repository.StudentRepository
import com.example.praktikum6.ui.detail.DetailViewModel

//class HomeViewModel(
//    private val repository: StudentRepository
//) : ViewModel() {
//
//    var uiState by mutableStateOf(HomeUiState())
//        private set
//    fun loadStudents() {
//        val students = repository.getStudents()
//        uiState = if (students != null) {
//            HomeUiState(students = students)
//        } else {
//            HomeUiState(errorMessage = "Tidak ada student")
//        }
//    }
//    companion object {
//        fun factory(repository: StudentRepository): ViewModelProvider.Factory =
//            object : ViewModelProvider.Factory {
//                @Suppress("UNCHECKED_CAST")
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    return HomeViewModel(repository) as T
//                }
//            }
//    }
//}
class HomeViewModel(
    private val repository: StudentRepository
) : ViewModel() {
    var uiState by mutableStateOf(HomeUiState())
        private set
    init { loadStudents() }
    fun loadStudents() {
        uiState = uiState.copy(isLoading = true, errorMessage = null)
        if (uiState.searchQuery != "") {
            repository.searchStudents(uiState.searchQuery) { result ->
                result
                    .onSuccess { data ->
                        uiState = HomeUiState(students = data)
                    }
                    .onFailure { error ->
                        uiState = HomeUiState(
                            errorMessage = error.message ?: "Gagal mengambil data"
                        )
                    }
            }
        }
        else {
            repository.getStudents { result ->
                result
                    .onSuccess { data ->
                        uiState = HomeUiState(students = data)
                    }
                    .onFailure { error ->
                        uiState = HomeUiState(
                            errorMessage = error.message ?: "Gagal mengambil data"
                        )
                    }
            }
        }
    }

    fun query(newQuery: String) {
        uiState.searchQuery = newQuery
    }

    companion object {
        fun factory(repository: StudentRepository): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return DetailViewModel(repository) as T
                }
            }
    }
}