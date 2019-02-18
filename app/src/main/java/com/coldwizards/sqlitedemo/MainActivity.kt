package com.coldwizards.sqlitedemo

import android.database.CursorIndexOutOfBoundsException
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView;
    private lateinit var editText: EditText;
    private lateinit var editText2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.display_name_tv)
        editText = findViewById(R.id.name_et)
        editText2 = findViewById(R.id.id_et)
    }

    fun addUser(view: View) {
        val dbHelper = DBHelper(this, null)
        val user = User(editText.text.toString())
        dbHelper.addName(user)
        Toast.makeText(this, "${user.userName}  添加到数据库", Toast.LENGTH_SHORT).show()
    }

    fun deleteUser(view: View) {
        val dbHelper = DBHelper(this, null)
        val id = editText2.text.toString()
        if (dbHelper.deleteUserById(id.toInt())) {
            Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
            editText2.setText("")
        } else {
            Toast.makeText(this, "删除失败", Toast.LENGTH_SHORT).show()
        }
    }

    fun showData(view: View) {
        textView.text = ""

        val dbHandler = DBHelper(this, null)
        try {
            val cursor = dbHandler.getAllUser()
            cursor!!.moveToFirst()
            var name = User(
                cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))
            )
            textView.append("id: ${name.id} name: ${name.userName}")
            textView.append("\n")
            while (cursor.moveToNext()) {
                var name = User(
                    cursor.getInt(cursor.getColumnIndex(DBHelper.COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.COLUMN_NAME))
                )
                textView.append("id: ${name.id} name: ${name.userName}")
                textView.append("\n")
            }
            cursor.close()
        } catch (e: CursorIndexOutOfBoundsException) {
            Toast.makeText(this, "没有数据", Toast.LENGTH_SHORT).show()
        }
    }

}
