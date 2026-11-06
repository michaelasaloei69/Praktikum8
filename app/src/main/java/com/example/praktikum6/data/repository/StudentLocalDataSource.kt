package com.example.praktikum6.data.repository

import com.example.praktikum6.data.model.Student

class StudentLocalDataSource {
    private val students = listOf(
        Student(2026, "Alya Putri", "Teknik Informatika", "2026"),
        Student(2027, "Bima Pratama", "Teknik Informatika", "2026"),
        Student(2028, "Citra Lestari", "Teknik Informatika", "2026"),
        Student(2029, "Adi", "Teknik Informatika", "2026"),
        Student(2030, "Joni", "Teknik Informatika", "2026"),
    )
    fun getStudentById(id: Int): Student? =
        students.find { it.id == id }

    fun getStudents(): List<Student> {
        return students
    }
}