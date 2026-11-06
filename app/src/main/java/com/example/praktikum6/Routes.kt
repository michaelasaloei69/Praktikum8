package com.example.praktikum6

object Routes {
    const val HOME = "home"
    const val PROFILE = "profile/{studentId}"
    const val DETAIL = "detail/{studentId}"
    const val ABOUT = "about/{studentId}"
    fun detail(studentId: Int): String = "detail/$studentId"
    fun about(studentId: Int): String = "about/$studentId"
    fun profile(studentId: Int): String = "profile/$studentId"
}