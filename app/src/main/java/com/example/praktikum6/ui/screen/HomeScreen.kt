package com.example.praktikum6.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praktikum6.data.model.Student
import com.example.praktikum6.ui.home.HomeUiState

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onOpenDetail: (Int) -> Unit,
    onOpenAbout: (Int) -> Unit,
    onOpenProfile: (Int) -> Unit
) {
    val studentList = uiState.students
    when {
        uiState.errorMessage != null ->
            Text(uiState.errorMessage)
        studentList != null ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                Text("Home", style = MaterialTheme.typography.headlineMedium)
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(
                        items = studentList
                    ) { student ->
                        StudentEntry(
                            student,
                            { onOpenDetail(student.id) },
                            { onOpenAbout(student.id) },
                            { onOpenProfile(student.id) }
                        )
                    }
                }
            }
    }
}

@Composable
fun StudentEntry(
    student: Student,
    onOpenDetail: (Int) -> Unit,
    onOpenAbout: (Int) -> Unit,
    onOpenProfile: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Id:${student.id}", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = { onOpenDetail(student.id) }) {
            Text("Buka Detail Mahasiswa ${student.id}")
        }
        Button(onClick = { onOpenAbout(student.id) }) {
            Text("Buka About Mahasiswa ${student.id}")
        }
        OutlinedButton(onClick = { onOpenProfile(student.id) }) {
            Text("Buka Profile ${student.id}")
        }
    }
}