package com.example.praktikum6.data.source

import com.example.praktikum6.data.model.Student
import com.example.praktikum6.data.repository.StudentLocalDataSource

//interface StudentRepository {
//    fun getStudentById(id: Int): Student?
//    fun getStudents(): List<Student>?
//}
//class DefaultStudentRepository(
//    private val dataSource: StudentLocalDataSource
//) : StudentRepository {
//    override fun getStudentById(id: Int): Student? =
//        dataSource.getStudentById(id)
//
//    override fun getStudents(): List<Student>? {
//        return dataSource.getStudents()
//    }
//}