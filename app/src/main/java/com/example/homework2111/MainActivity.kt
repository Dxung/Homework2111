package com.example.homework2111

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var students: MutableList<StudentDataModel> = mutableListOf()
    private lateinit var studentAdapter : customAdaptor
    private var itemChoosingPos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initProps()
        initAdaptor();

    }

    private fun initProps(){
        //Register ListView
        val listView =  findViewById<ListView>(R.id.main_listView)
        registerForContextMenu(listView)

        //toolbar
        val toolbar: Toolbar = findViewById(R.id.main_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Student Manager"
    }

    private fun initAdaptor(){
        students.addAll(listOf(
            StudentDataModel("Nguyễn Văn An", "SV001"),
            StudentDataModel("Trần Thị Bảo", "SV002"),
            StudentDataModel("Lê Hoàng Cường", "SV003"),
            StudentDataModel("Phạm Thị Dung", "SV004"),
            StudentDataModel("Đỗ Minh Đức", "SV005"),
            StudentDataModel("Vũ Thị Hoa", "SV006"),
            StudentDataModel("Hoàng Văn Hải", "SV007"),
            StudentDataModel("Bùi Thị Hạnh", "SV008"),
            StudentDataModel("Đinh Văn Hùng", "SV009"),
            StudentDataModel("Nguyễn Thị Linh", "SV010"),
            StudentDataModel("Phạm Văn Long", "SV011"),
            StudentDataModel("Trần Thị Mai", "SV012"),
            StudentDataModel("Lê Thị Ngọc", "SV013"),
            StudentDataModel("Vũ Văn Nam", "SV014"),
            StudentDataModel("Hoàng Thị Phương", "SV015"),
            StudentDataModel("Đỗ Văn Quân", "SV016"),
            StudentDataModel("Nguyễn Thị Thu", "SV017"),
            StudentDataModel("Trần Văn Tài", "SV018"),
            StudentDataModel("Phạm Thị Tuyết", "SV019"),
            StudentDataModel("Lê Văn Vũ", "SV020")
        ))

        val listView: ListView = findViewById(R.id.main_listView)
        studentAdapter = customAdaptor(this,students)
        listView.adapter = studentAdapter
    }

    private val launcherAdd = registerForActivityResult(ActivityResultContracts.StartActivityForResult()
    ) { it: ActivityResult ->
        if (it.resultCode == RESULT_OK) {
            val studentAddedName = it.data?.getStringExtra("studentName")
            val studentAddedId = it.data?.getStringExtra("studentId")
            if (studentAddedName != null && studentAddedId != null) {
                addStudentData(studentAddedName, studentAddedId)
                Toast.makeText(this, "Student Added", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Add cancelled", Toast.LENGTH_SHORT).show()
        }
    }


    private val launcherEdit = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
       ) { it: ActivityResult ->
           if (it.resultCode == RESULT_OK) {
               val studentEditedName = it.data?.getStringExtra("studentName")
               val studentEditedId = it.data?.getStringExtra("studentId")
               if (studentEditedName != null && studentEditedId != null) {
                   updateStudentData(studentEditedName,studentEditedId,itemChoosingPos)
                   Toast.makeText(this, "Student Edited", Toast.LENGTH_SHORT).show()
               }


           } else {
               Toast.makeText(this, "edit cancelled", Toast.LENGTH_SHORT).show()
           }
       }



    /*--- Edit Menu ---*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.optionmenu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addNew -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                launcherAdd.launch(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    /*--- Context Menu ---*/
    //create context menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.contextmenu, menu)
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    //context menu
    override fun onContextItemSelected(item: MenuItem): Boolean {
        itemChoosingPos = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val student = students[itemChoosingPos]

        when (item.itemId) {
            R.id.edit -> {
                val intent = Intent(this, EditStudentActivity::class.java)
                intent.putExtra("studentName", student.studentName)
                intent.putExtra("studentId", student.studentId)
                launcherEdit.launch(intent)
                return true
            }
            R.id.remove -> {
                removeStudentData(itemChoosingPos)
                Toast.makeText(this, "student removed", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }


    //Adaptor functions setting
    private fun updateStudentData(studentEditedName: String, studentEditedId: String, pos:Int){
        students[pos] = StudentDataModel(studentEditedName,studentEditedId)
        studentAdapter.notifyDataSetChanged()
    }

    private fun removeStudentData(pos:Int){
        students.removeAt(pos)
        studentAdapter.notifyDataSetChanged()
    }

    private fun addStudentData(studentName: String, studentId: String){
        students.add(StudentDataModel(studentName,studentId))
        studentAdapter.notifyDataSetChanged()
    }


}