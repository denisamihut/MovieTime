package com.example.movieapp.ui.tab_activity

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityTabbedMainBinding

class TabbedMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTabbedMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTabbedMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fab: FloatingActionButton = binding.fab

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}