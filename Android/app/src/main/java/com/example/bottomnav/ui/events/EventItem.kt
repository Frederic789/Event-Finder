package com.example.bottomnav.ui.events

// com.example.bottomnav.ui.events.EventItem.kt
data class EventItem(
    val title: String,
    val date: String,
    val location: String,
    val locationLatitude: Double,
    val locationLongitude: Double,
    var distanceFromUser: Float = 0f // Default to 0, update later
)

