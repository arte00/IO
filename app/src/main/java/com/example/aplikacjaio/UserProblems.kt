package com.example.aplikacjaio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikacjaio.exampleData.DataProblem
import kotlinx.android.synthetic.main.activity_user_problems.*
import kotlinx.android.synthetic.main.add_problem_dialog.view.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class UserProblems : AppCompatActivity() {

    private var exampleList = generateProblems(10)
    // private val adapter = AdapterUserProblems(exampleList)

    private lateinit var toggle : ActionBarDrawerToggle

    // private val status = getData()
    // private val exampleList = changeList(status)
    // private val adapter = AdapterUserProblems(exampleList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(R.layout.activity_user_problems)

        // Set up recycler view
        exampleList = changeList(getData())
        up_recycler_viewer.adapter = AdapterUserProblems(exampleList)
        up_recycler_viewer.layoutManager = LinearLayoutManager(this)
        up_recycler_viewer.setHasFixedSize(true)

        // Add problem dialog

        button_add_problem.setOnClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_problem_dialog, null)
            val mBuilder = AlertDialog.Builder(this).setView(mDialogView)
            val mAlertDialog = mBuilder.show()

            mDialogView.button_add.setOnClickListener {

                val databaseHandler: DatabaseHandler = DatabaseHandler(this)

                val title = mDialogView.edit_title.text.toString()
                val place = mDialogView.edit_place.text.toString()
                val description = mDialogView.edit_description.text.toString()
                if (title.isNotEmpty() && place.isNotEmpty() && description.isNotEmpty()) {
                    // insertItem(title, place, description, 1)

                    val status = databaseHandler.addProblem(DataProblem(0, title, description, place))

                    if(status > -1){
                        //insertItem(up_recycler_viewer.adapter as AdapterUserProblems, title, place, description, 1)
                        println("0")
                        insertItem(up_recycler_viewer.adapter as AdapterUserProblems, title, place, description, 2)
                        // println(exampleList)

                    }

                    mAlertDialog.dismiss()
                } else {
                    Toast.makeText(this , "Wypełnij wszystkie pola!", Toast.LENGTH_SHORT).show()
                }
            }
            mDialogView.button_cancel.setOnClickListener {
                mAlertDialog.dismiss()
            }

        }

        // Menu drawer set up

        toggle = ActionBarDrawerToggle(this, drawer_layout, R.string.open, R.string.close)
        drawer_layout.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_notification -> {
                    val intent = Intent(applicationContext, UserProblems::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_bus -> {
                    val intent = Intent(applicationContext, UserBuses::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_station -> Toast.makeText(applicationContext, "Stacje", Toast.LENGTH_SHORT).show()
                R.id.nav_login -> {
                    val intent = Intent(applicationContext, Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            true

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertItem(adapter: AdapterUserProblems, title: String, place: String, description: String, priority: Int){

        val newItem = UserProblemItem(R.drawable.ic_up, title, description, priority)

        exampleList.add(exampleList.size, newItem)
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
        /*val exList: ArrayList<DataProblem> = getData()
        val nextList: ArrayList<UserProblemItem> = changeList(exList)
        for(elem in nextList){
            list += elem
        }
        println(list)*/
        return list
    }

    private fun getData(): ArrayList<DataProblem> {
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        return databaseHandler.viewProblem()
    }

    private fun changeList(list: ArrayList<DataProblem>): ArrayList<UserProblemItem>{
        val result = ArrayList<UserProblemItem>()
        for(problem in list){
            val priority = (Random.nextInt(0, 100) % 4) + 1
            result.add(UserProblemItem(R.drawable.ic_up, problem.title, problem.description, priority))
        }
        return result
    }

}