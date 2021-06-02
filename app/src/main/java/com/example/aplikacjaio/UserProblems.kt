package com.example.aplikacjaio

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_problems.*
import kotlinx.android.synthetic.main.add_problem_dialog.view.*
import kotlin.random.Random

class UserProblems : AppCompatActivity() {

    private val exampleList = generateProblems(10)
    val adapter = AdapterUserProblems(exampleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_problems)

        up_recycler_viewer.adapter = adapter
        up_recycler_viewer.layoutManager = LinearLayoutManager(this)
        up_recycler_viewer.setHasFixedSize(true)

        button_add_problem.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_problem_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            mDialogView.button_add.setOnClickListener {

                val title = mDialogView.edit_title.text.toString()
                val place = mDialogView.edit_place.text.toString()
                val description = mDialogView.edit_description.text.toString()
                if (title.isNotEmpty() && place.isNotEmpty() && description.isNotEmpty()) {
                    insertItem(title, place, description, 1)
                    mAlertDialog.dismiss()
                } else {
                    Toast.makeText(this , "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show()
                }
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()

            }

        }

    }

    private fun insertItem(title: String, place: String, description: String, priority: Int){

        val newItem = UserProblemItem(R.drawable.ic_up, title, description, priority)

        exampleList.add(0, newItem)
        adapter.notifyItemInserted(0)

    }

    private fun deleteItem(index: Int){

    }

    private fun generateProblems(size: Int): ArrayList<UserProblemItem>{

        val list = ArrayList<UserProblemItem>()

        for(i in 0 until size){
            val priority = (i % 4) + 1
            val item = UserProblemItem(R.drawable.ic_up,
                "Problem title nr $i",
                "Short explanation of problem number $i. Very short explanation" +
                        "of problem, as short as possible. Cannot be shorter.", priority)

            list += item
        }
        return list
    }

}