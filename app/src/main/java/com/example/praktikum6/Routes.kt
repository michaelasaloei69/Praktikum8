package com.example.praktikum6

object Routes {
    const val HOME = "home"
    const val PROFILE = "profile"
    const val DETAIL = "detail/{studentId}"
    const val ABOUT = "about/{studentId}"
    fun detail(studentId: Int): String = "detail/$studentId"
    fun about(studentId: Int): String = "about/$studentId"
}