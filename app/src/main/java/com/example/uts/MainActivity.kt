package com.example.uts

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameField = findViewById<EditText>(R.id.username)
        val passwordField = findViewById<EditText>(R.id.pasword)
        val loginButton = findViewById<Button>(R.id.tombol)

        loginButton.setOnClickListener {
            val enteredUsername = usernameField.text.toString()
            val enteredPassword = passwordField.text.toString()
            if (enteredUsername == "saidil" && enteredPassword == "1234") {
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
