package com.example.dreamsleepapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class EditDreamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_dream)
        val dreamDetails = findViewById<EditText>(R.id.editDreamText)
        val saveButton = findViewById<Button>(R.id.saveButton)
        val sleepDate = findViewById<TextView>(R.id.sleepDate)
        val sleepHours = findViewById<TextView>(R.id.sleepHours)
        sleepDate.text = intent.getStringExtra("sleep date")
        sleepHours.text = (intent.getIntExtra("sleep hours", 0)).toString() + " hours of sleep"
        saveButton.setOnClickListener {
            val resultIntent = Intent()
            resultIntent.putExtra("dream details", dreamDetails.text.toString())
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}