package com.example.aplikacjaio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_user_buses.*
import kotlinx.android.synthetic.main.activity_user_problems.*

class UserBuses : AppCompatActivity() {

    private val exampleList = generateProblems(10)
    private val adapter = AdapterUserBuses(exampleList)

    private lateinit var toggle : ActionBarDrawerToggle


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_buses)

        ub_recycler_viewer.adapter = adapter
        ub_recycler_viewer.layoutManager = LinearLayoutManager(this)
        ub_recycler_viewer.setHasFixedSize(true)
        toggle = ActionBarDrawerToggle(this, drawer_layout_buses, R.string.open, R.string.close)
        drawer_layout_buses.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view_bus.setNavigationItemSelectedListener {

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



    private fun generateProblems(size: Int): ArrayList<UserBusItem>{
        val list = ArrayList<UserBusItem>()

        for(i in 0 until size){
            val access = (i % 7) + 1
            val item = UserBusItem(R.drawable.ic_bus, 53, 650, "Solaris III", access)

            list += item
        }
        return list
    }
}