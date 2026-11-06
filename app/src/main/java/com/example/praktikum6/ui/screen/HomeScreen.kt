package com.example.praktikum6.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.praktikum6.data.model.Student
import com.example.praktikum6.ui.home.HomeUiState
import com.example.praktikum6.ui.home.HomeViewModel


@Composable
fun HomeScreen(
    uiState: HomeUiState,
    homeViewModel: HomeViewModel,
    onOpenDetail: (Int) -> Unit,
    onOpenAbout: (Int) -> Unit,
    onOpenProfile: (Int) -> Unit,
    onStudentClick: (Int) -> Unit,
    onRetry: () -> Unit
) {
    var query by remember{ mutableStateOf(uiState.searchQuery) }
    when {
        uiState.isLoading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        uiState.errorMessage != null -> {
            Column(
                modifier = Modifier.fillMaxSize().padding(24.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(uiState.errorMessage)
                Button(onClick = onRetry) { Text("Coba Lagi") }
            }
        }
        else -> {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ) {
                LazyColumn {
                    items(items=uiState.students ?: listOf<Student>()) { student ->
                        TextButton(onClick = { onStudentClick(student.id) }) {
                            Text("${student.id} - ${student.name}")
                        }
                    }
                }

                TextField(
                    value= query,
                    singleLine = true,
                    shape = shapes.large,
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {
                        homeViewModel.query(it)
                        query=it
                                    },
                    label = {Text("Search User")},
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { homeViewModel.loadStudents() }
                    )
                )
            }
        }
    }
}


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