package com.teacher.educamobile.android

sealed class NavItem(
    val title: String,
    val icon: Int,
    val route: String
) {
    object Meetings:NavItem("Reuniones", R.drawable.calendar_plus, "A")
    object Attendance:NavItem("Asistencia", R.drawable.calendar_plus, "B")
    object Notifications:NavItem("Notificaciones", R.drawable.calendar_plus, "C")
}