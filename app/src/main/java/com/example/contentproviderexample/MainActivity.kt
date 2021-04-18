package com.example.contentproviderexample

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        var helper = MyHelper(applicationContext)
//        var db = helper.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM AC_TABLE", null)
//
//        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show()
//
//        if(rs.moveToFirst()) {
//            Toast.makeText(applicationContext, rs.getString(1), Toast.LENGTH_LONG).show()
//            Log.d("content_bx", rs.getString(1))
//        }

        var rs = contentResolver.query(
            AcronymProvider.CONTENT_URI,
            arrayOf(AcronymProvider.ID, AcronymProvider.NAME, AcronymProvider.MEANING),
            null, null, AcronymProvider.NAME)

        var btnNext = findViewById<Button>(R.id.button4)
        var btnPrev = findViewById<Button>(R.id.button5)
        var btnInsert = findViewById<Button>(R.id.button6)
        var btnUpdate = findViewById<Button>(R.id.button7)
        var btnDelete = findViewById<Button>(R.id.button8)
        var btnClear = findViewById<Button>(R.id.button9)
        var nameEditText = findViewById<EditText>(R.id.editTextTextPersonName)
        var meaningEditText = findViewById<EditText>(R.id.editTextTextPersonName2)

        btnNext.setOnClickListener {
            if(rs?.moveToNext()!!){
                nameEditText.setText(rs.getString(1))
                meaningEditText.setText(rs.getString(2))
            }
        }

        btnPrev.setOnClickListener {
            if(rs?.moveToPrevious()!!){
                nameEditText.setText(rs.getString(1))
                meaningEditText.setText(rs.getString(2))
            }
        }

        btnClear.setOnClickListener {
            nameEditText.setText("")
            meaningEditText.setText("")
            nameEditText.requestFocus()
        }


        btnInsert.setOnClickListener {
            var contents = ContentValues()
            contents.put(AcronymProvider.NAME, nameEditText.text.toString())
            contents.put(AcronymProvider.MEANING, meaningEditText.text.toString())
            contentResolver.insert(AcronymProvider.CONTENT_URI, contents)
            rs?.requery()
        }

        btnUpdate.setOnClickListener {
            var contents = ContentValues()
            contents.put(AcronymProvider.MEANING, meaningEditText.text.toString())
            contentResolver.update(AcronymProvider.CONTENT_URI, contents, "NAME = ?", arrayOf(nameEditText.text.toString()))
            rs?.requery()
        }

        btnDelete.setOnClickListener {
            contentResolver.delete(AcronymProvider.CONTENT_URI, "NAME = ?", arrayOf(nameEditText.text.toString()))
            rs?.requery()
        }

    }
}