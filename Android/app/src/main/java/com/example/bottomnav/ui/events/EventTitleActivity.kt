package com.example.bottomnav.ui.events

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnav.R

class EventTitleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_title)

        // Reference to the ImageView
        val eventImageView: ImageView = findViewById(R.id.eventImage)

        // Condition to determine which image to display
        val shouldShowSpecialImage = false // Set this based on your app's logic

        // Dynamically set the image based on the condition
        if (shouldShowSpecialImage) {
            eventImageView.setImageResource(R.drawable.your_event_image) // Replace 'special_image' with the actual drawable resource if condition is true
        } else {
            eventImageView.setImageResource(R.drawable.your_event_image) // Default image
        }

        // Setting up the button to handle click events
        val saveButton: Button = findViewById(R.id.save_event_button)
        saveButton.setOnClickListener {
            // Handle save button click
            // You might want to change the image again here or perform other UI updates
        }
    }
}
