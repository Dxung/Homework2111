package com.example.homework2111

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_student_layout)

        val addStudentName = findViewById<EditText>(R.id.edit_student_name)
        val addStudentId = findViewById<EditText>(R.id.edit_student_id)

        findViewById<Button>(R.id.button_ok).setOnClickListener {
            val editedName = addStudentName.text.toString()
            val editedId = addStudentId.text.toString()

            intent.putExtra("studentName", editedName)
            intent.putExtra("studentId", editedId)

            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        findViewById<Button>(R.id.button_cancel).setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}