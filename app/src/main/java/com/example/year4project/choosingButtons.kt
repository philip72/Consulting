package com.example.year4project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button


class choosingButtons : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choosing_buttons)

        val patientSignIn = findViewById<Button>(R.id.PatientButton)
        patientSignIn.setOnClickListener {
            val intent = Intent(this,patientLogin::class.java)
            startActivity(intent)

        }
        val specialistSignIn = findViewById<Button>(R.id.SpecilaistButton)
        specialistSignIn.setOnClickListener {
            val intent = Intent(this,specialistLogin::class.java)
            startActivity(intent)
        }

    }
}