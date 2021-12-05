package com.example.macruduinative.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macruduinative.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivityDB : AppCompatActivity() {

    private lateinit var drugViewModel: DrugViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = DrugListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Get a new or existing ViewModel from the ViewModelProvider.
        drugViewModel = ViewModelProvider(this).get(DrugViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        drugViewModel.allDrugs.observe(this, { drugs ->
            // Update the cached copy of the words in the adapter.
            drugs?.let { adapter.setDrugs(it) }
        })

        adapter.onItemClick = {
            val intent = Intent(this@MainActivityDB, DrugDetailsActivity::class.java)
            intent.putExtra("ID", it.id)
            intent.putExtra("name", it.name)
            intent.putExtra("price", it.price.toString())
            intent.putExtra("producer", it.producer)
            intent.putExtra("provider", it.provider)
            intent.putExtra("quantity", it.quantity.toString())
            drugDetailsActivityLauncher.launch(intent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivityDB, NewDrugActivity::class.java)
            newDrugActivityLauncher.launch(intent)
        }

    }

    private val newDrugActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    drugViewModel.insert(data.getStringExtra("ID")!!, data.getStringExtra("name")!!, data.getStringExtra("price")!!, data.getStringExtra("producer")!!, data.getStringExtra("provider")!!, data.getStringExtra("quantity")!!)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    private val drugDetailsActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    if(data.getStringExtra("name") == null) {
                        drugViewModel.delete(data.getStringExtra("ID")!!)
                    }
                    else {
                        drugViewModel.update(data.getStringExtra("ID")!!, data.getStringExtra("name")!!, data.getStringExtra("price")!!, data.getStringExtra("producer")!!, data.getStringExtra("provider")!!, data.getStringExtra("quantity")!!)
                    }
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.noAction,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
}

