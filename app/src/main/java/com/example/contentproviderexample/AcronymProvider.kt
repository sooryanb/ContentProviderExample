package com.example.contentproviderexample

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.net.Uri

class AcronymProvider: ContentProvider() {

    companion object{
        val PROVIDER_NAME = "com.example.contentproviderexample/AcronymProvider"
        val URL = "content://$PROVIDER_NAME/AC_TABLE"
        val CONTENT_URI = Uri.parse(URL)

        val ID = "_id"
        val NAME = "NAME"
        val MEANING = "MEANING"
    }

    lateinit var db: SQLiteDatabase

    override fun onCreate(): Boolean {
        var helper = MyHelper(context)
        db = helper.writableDatabase
        return if(db==null) false else true
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        return db.query("AC_TABLE", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun getType(uri: Uri): String? {
        return "vnd.android.cursor.dir/vnd.example.ac_table"
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        db.insert("AC_TABLE", null, values)
        context?.contentResolver?.notifyChange(uri, null)
        return uri
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        var count = db.delete("AC_TABLE", selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        var count = db.update("AC_TABLE", values, selection, selectionArgs)
        context?.contentResolver?.notifyChange(uri, null)
        return count
    }
}