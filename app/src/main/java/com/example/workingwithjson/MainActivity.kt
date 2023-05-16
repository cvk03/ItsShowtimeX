package com.example.workingwithjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)

// Set the Toolbar as the action bar for the activity
        setSupportActionBar(toolbar)


        val fragmentManager : FragmentManager = supportFragmentManager
        val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
        val fragment1 = Fragment1()

        fragmentTransaction.add(R.id.frame1,fragment1)
                           .addToBackStack("MyFragmentTag")
        fragmentTransaction.commit()


    }
    fun showProfile(){
        val fragment = Fragment3() // replace MyFragment with the name of your Fragment class
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame1, fragment)
            .addToBackStack(null)
            .commit()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val fragment = supportFragmentManager.findFragmentByTag("MyFragmentTag")
        when (item.itemId) {
            R.id.Profile -> {
                showProfile()
                return true
            }
            R.id.Show_more -> {
                val fragment = supportFragmentManager.findFragmentById(R.id.frame1)
                Log.d("MainActivity", "Current fragment: ${fragment?.javaClass?.name}")
                if (fragment is Fragment1) {
                    fragment.showMoreMovies()
                }
                return true
            }
            R.id.back->{
                val fragment = Fragment1() // replace MyFragment with the name of your Fragment class
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame1, fragment)
                    .addToBackStack(null)
                    .commit()

                return true

            }
            else -> return super.onOptionsItemSelected(item)
        }
    }


}