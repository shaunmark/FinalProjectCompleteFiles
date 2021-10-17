package com.example.finalprojectapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val logBookButton = findViewById<Button>(R.id.logBookButton)
        val searchButton = findViewById<Button>(R.id.searchButton)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val deleteButton = findViewById<Button>(R.id.deleteButton)

        logBookButton.setOnClickListener {
            startActivity(Intent(this, LogBookPage::class.java))
        }

        searchButton.setOnClickListener {
            startActivity(Intent(this, SearchVehiclePage::class.java))
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterNewVehicle::class.java))
        }

        deleteButton.setOnClickListener {
            startActivity(Intent(this, DeletePage::class.java))
        }

    }
}