package com.example.homework2111

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class EditStudentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_student_layout)

        val studentReceivedName = intent.getStringExtra("studentName")
        val studentReceivedId = intent.getStringExtra("studentId")

        val nameEditText: EditText = findViewById(R.id.edit_student_name)
        val idEditText: EditText = findViewById(R.id.edit_student_id)

        nameEditText.setText(studentReceivedName)
        idEditText.setText(studentReceivedId)


        //submit button
        val submitButton: Button = findViewById(R.id.button_ok)
        submitButton.setOnClickListener {
            val updatedName = nameEditText.text.toString()
            val updatedId = idEditText.text.toString()

            //return data to main_activity
            val resultIntent = Intent().apply {
                putExtra("studentName", updatedName)
                putExtra("studentId", updatedId)
            }
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }

        //cancel button
        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }

    }
}