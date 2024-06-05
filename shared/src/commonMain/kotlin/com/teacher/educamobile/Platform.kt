package com.teacher.educamobile

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform