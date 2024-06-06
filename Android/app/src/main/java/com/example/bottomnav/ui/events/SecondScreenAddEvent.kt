package com.example.bottomnav.ui.events

import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bottomnav.R

class SecondScreenAddEvent : AppCompatActivity() {

    companion object {
        const val REQUEST_IMAGE_CAPTURE = 1
        const val REQUEST_IMAGE_SELECT = 2
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_screen_add_event)

        findViewById<Button>(R.id.buttonTakePhoto).setOnClickListener {
            dispatchTakePictureIntent()
        }

        findViewById<Button>(R.id.buttonSelectPhoto).setOnClickListener {
            dispatchGalleryIntent()
        }
    }

    private fun dispatchTakePictureIntent() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (takePictureIntent.resolveActivity(packageManager) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } else {
            // Handle the case where no application can handle the intent
        }
    }


    private fun dispatchGalleryIntent() {
        val pickPhotoIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickPhotoIntent, REQUEST_IMAGE_SELECT)
    }

    // Handle the results of camera or gallery intents
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE -> {
                    // Handle the captured image
                }
                REQUEST_IMAGE_SELECT -> {
                    // Handle the selected image from gallery
                }
            }
        }
    }
}
