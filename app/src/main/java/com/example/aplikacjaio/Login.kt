package com.example.aplikacjaio

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user_problems.*
import kotlinx.android.synthetic.main.activity_user_problems.nav_view

class Login : AppCompatActivity() {

    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        toggle = ActionBarDrawerToggle(this, drawer_layout_login, R.string.open, R.string.close)
        drawer_layout_login.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        nav_view_login.setNavigationItemSelectedListener {

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



}