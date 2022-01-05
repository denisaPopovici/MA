package com.example.macruduinative.database

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.macruduinative.NetworkBroadcastReceiver
import com.example.macruduinative.R
import com.example.macruduinative.server.Manager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.IllegalArgumentException

class MainActivityDB : AppCompatActivity() {
    private lateinit var broadcastReceiver : BroadcastReceiver;
    private lateinit var drugViewModel: DrugViewModel
    private lateinit var manager: Manager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = DrugListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        manager = Manager()
        drugViewModel = ViewModelProvider(this).get(DrugViewModel::class.java)

        drugViewModel.drugs.observe(this, { drugs ->
            // Update the cached copy of the drugs in the adapter.
            drugs?.let { adapter.setDrugs(it) }
        })

        loadDrugs()

        adapter.onItemClick = {
            val intent = Intent(this@MainActivityDB, DrugDetailsActivity::class.java)
            intent.putExtra("ID", it.id.toString())
            intent.putExtra("name", it.name)
            intent.putExtra("price", it.price.toString())
            intent.putExtra("producer", it.producer)
            intent.putExtra("provider", it.provider)
            intent.putExtra("quantity", it.quantity.toString())
            intent.putExtra("is_offline", it.is_offline.toString())
            drugDetailsActivityLauncher.launch(intent)
        }

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivityDB, NewDrugActivity::class.java)
            newDrugActivityLauncher.launch(intent)
        }

        broadcastReceiver = NetworkBroadcastReceiver(drugViewModel)
        registerNetworkBroadcastReceiver()

    }

    private fun registerNetworkBroadcastReceiver(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
            registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    private fun unregisterNetwork(){
        try {
            unregisterReceiver(broadcastReceiver);
        }catch (e: IllegalArgumentException){
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterNetwork();
    }

    private fun loadDrugs() {
        drugViewModel.fetchDrugsFromNetwork()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    private val newDrugActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    if (manager.networkConnectivity(this)) {
                            //server add - id is not important
                            val drug = Drug(-1, data.getStringExtra("name")!!, data.getStringExtra("price")!!.toFloat(), data.getStringExtra("producer")!!, data.getStringExtra("provider")!!, data.getStringExtra("quantity")!!.toInt(), false)
                            drugViewModel.addDrug(drug)
                    }
                    else
                        //add in db
                        drugViewModel.insert(-1, data.getStringExtra("name")!!, data.getStringExtra("price")!!, data.getStringExtra("producer")!!, data.getStringExtra("provider")!!, data.getStringExtra("quantity")!!, true)
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            }
        }

    @RequiresApi(Build.VERSION_CODES.M)
    private val drugDetailsActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                if (data != null) {
                    if (data.getStringExtra("name") == null) {
                        //delete
                        if (manager.networkConnectivity(this)) {
                            //delete server
                            drugViewModel.removeDrug(data.getStringExtra("ID")!!.toInt())
                        } else
                        //show message that we are not online
                            Toast.makeText(
                                applicationContext,
                                "The application is offline. This operation is not available.",
                                Toast.LENGTH_LONG
                            ).show()
                    } else {
                        //update
                        if (manager.networkConnectivity(this)) {
                            //update server
                            val drug = Drug(
                                data.getStringExtra("ID")!!.toInt(),
                                data.getStringExtra("name")!!,
                                data.getStringExtra("price")!!.toFloat(),
                                data.getStringExtra("producer")!!,
                                data.getStringExtra("provider")!!,
                                data.getStringExtra("quantity")!!.toInt(),
                                data.getStringExtra("is_offline")!!.toBoolean(),
                            )
                            drugViewModel.updateDrug(drug)
                        } else {
                            //show message that we are not online
                            Toast.makeText(
                                applicationContext,
                                "The application is offline. This operation is not available.",
                                Toast.LENGTH_LONG
                            ).show()
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
}

