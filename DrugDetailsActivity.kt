package com.example.macruduinative.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.macruduinative.R

class DrugDetailsActivity : AppCompatActivity() {
    private lateinit var detailsNameView: EditText
    private lateinit var detailsPriceView: EditText
    private lateinit var detailsProducerView: EditText
    private lateinit var detailsProviderView: EditText
    private lateinit var detailsQuantityView: EditText



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        detailsNameView = findViewById(R.id.details_name)
        detailsPriceView = findViewById(R.id.details_price)
        detailsProducerView = findViewById(R.id.details_producer)
        detailsProviderView = findViewById(R.id.details_provider)
        detailsQuantityView = findViewById(R.id.details_quantity)

        val id = intent.getStringExtra("ID")
        Log.d("TAG", "ID: " + id.toString())
        val name = intent.getStringExtra("name")
        val price = intent.getStringExtra("price")
        val producer = intent.getStringExtra("producer")
        val provider = intent.getStringExtra("provider")
        val quantity = intent.getStringExtra("quantity")
        val is_offline = intent.getStringExtra("is_offline")

        detailsNameView.setText(name)
        detailsPriceView.setText(price)
        detailsProducerView.setText(producer)
        detailsProviderView.setText(provider)
        detailsQuantityView.setText(quantity)

        val buttonDelete = findViewById<Button>(R.id.button_delete)
        val buttonUpdate = findViewById<Button>(R.id.button_update)

        buttonDelete.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra("ID", id)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        buttonUpdate.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(detailsNameView.text) || TextUtils.isEmpty(detailsPriceView.text) || TextUtils.isEmpty(detailsProducerView.text) || TextUtils.isEmpty(detailsProviderView.text) || TextUtils.isEmpty(detailsQuantityView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val newName = detailsNameView.text.toString()
                val newPrice = detailsPriceView.text.toString()
                val newProducer = detailsProducerView.text.toString()
                val newProvider = detailsProviderView.text.toString()
                val newQuantity = detailsQuantityView.text.toString()
                replyIntent.putExtra("ID", id)
                replyIntent.putExtra("name", newName)
                replyIntent.putExtra("price", newPrice)
                replyIntent.putExtra("producer", newProducer)
                replyIntent.putExtra("provider", newProvider)
                replyIntent.putExtra("quantity", newQuantity)
                replyIntent.putExtra("is_offline", is_offline)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}