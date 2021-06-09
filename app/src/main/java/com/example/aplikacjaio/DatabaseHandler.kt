package com.example.aplikacjaio

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.provider.ContactsContract
import com.example.aplikacjaio.exampleData.DataProblem

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "Database"
        private const val TABLE_CONTACTS = "ProblemsTable"

        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_PLACE = "place"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_CONTACTS_TABLE = ("CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TITLE + " TEXT,"
                + KEY_DESCRIPTION + " TEXT," + KEY_PLACE + " TEXT" + ")")
        db?.execSQL(CREATE_CONTACTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int){
        //something
    }

    fun addProblem(problem: DataProblem): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, problem.title)
        contentValues.put(KEY_DESCRIPTION, problem.description)
        contentValues.put(KEY_PLACE, problem.place)

        val success = db.insert(TABLE_CONTACTS, null, contentValues)
        db.close()

        return success
    }

    fun viewProblem(): ArrayList<DataProblem> {

        val problemList: ArrayList<DataProblem> = ArrayList<DataProblem>()
        val selectQuery = "SELECT * FROM $TABLE_CONTACTS"

        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery, null)
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }

        var id: Int
        var title: String
        var description: String
        var place: String

        if(cursor.moveToFirst()){
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                description = cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))
                place = cursor.getString(cursor.getColumnIndex(KEY_PLACE))

                val prob = DataProblem(id = id, title = title, description = description, place = place)
                problemList.add(prob)
            } while (cursor.moveToNext())
        }
        return problemList
    }

}