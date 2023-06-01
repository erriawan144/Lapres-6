package com.example.lapres_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.menu_todo -> {
                val todoFragment = TodoFragment()
                openFragment(todoFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_inprogress -> {
                val inProgressFragment = InProgressFragment()
                openFragment(inProgressFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_done -> {
                val doneFragment = DoneFragment()
                openFragment(doneFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)

        val defaultFragment = TodoFragment()
        openFragment(defaultFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}