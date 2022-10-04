package com.akhil.flixster2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.akhil.flixster.ShowFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val supportFragmentManager = supportFragmentManager
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content, ShowFragment(), null).commit()
    }
}