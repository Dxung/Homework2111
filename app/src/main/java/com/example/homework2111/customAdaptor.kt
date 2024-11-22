package com.example.homework2111

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView

class customAdaptor(context : Context,private val itemList: MutableList<StudentDataModel>): ArrayAdapter<StudentDataModel>(context,0,itemList)  {

    private class ViewHolder(view: View){
        val studentNameTextView: TextView = view.findViewById(R.id.student_name_textView)
        val studentIdTextView: TextView = view.findViewById(R.id.student_id_textView)
    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var newConvertView = convertView

        val viewHolder: ViewHolder

        if (newConvertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            newConvertView = inflater.inflate(R.layout.item_layout, parent, false)

            viewHolder = ViewHolder(newConvertView)
            newConvertView.tag = viewHolder
        } else {
            viewHolder = newConvertView.tag as ViewHolder
        }

        val item = itemList[position]

        viewHolder.studentNameTextView.setText(item.studentName)
        viewHolder.studentIdTextView.setText(item.studentId)

        return newConvertView!!
    }

}