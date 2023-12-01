package com.example.dreamsleepapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val bottomNavBar : BottomNavigationView = findViewById(R.id.bottomNavigationView)




        bottomNavBar.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, HomeFragment.newInstance("","")).commit()
                }
                R.id.histItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, SleepHistoryFragment.newInstance("","")).commit()

                }
                R.id.statsItem -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fragmentContainerView, DreamFragment.newInstance("", "")).commit()
                }
            }

            true
        }
    }
    fun openDreamLogFragment(sleep: Sleep) {
        val fragment = SleepLogFragment().apply {
            arguments = Bundle().apply {
                putInt("hrsSlept", sleep.hrs.toInt())
                putString("dream", sleep.dream)
                putInt("rating", sleep.rating.toInt())
                putInt("id", sleep.id.toInt())
            }
        }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment)
            .addToBackStack(null)
            .commit()
    }
}