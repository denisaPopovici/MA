package com.example.macruduinative.database

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.macruduinative.R

class NewDrugActivity : AppCompatActivity() {
    private lateinit var editNameView: EditText
    private lateinit var editPriceView: EditText
    private lateinit var editProducerView: EditText
    private lateinit var editProviderView: EditText
    private lateinit var editQuantityView: EditText



    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_drug)
        editNameView = findViewById(R.id.edit_name)
        editPriceView = findViewById(R.id.edit_price)
        editProducerView = findViewById(R.id.edit_producer)
        editProviderView = findViewById(R.id.edit_provider)
        editQuantityView = findViewById(R.id.edit_quantity)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editNameView.text) || TextUtils.isEmpty(editPriceView.text) || TextUtils.isEmpty(editProducerView.text) || TextUtils.isEmpty(editProviderView.text) || TextUtils.isEmpty(editQuantityView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val name = editNameView.text.toString()
                val price = editPriceView.text.toString()
                val producer = editProducerView.text.toString()
                val provider = editProviderView.text.toString()
                val quantity = editQuantityView.text.toString()

                replyIntent.putExtra("name", name)
                replyIntent.putExtra("price", price)
                replyIntent.putExtra("producer", producer)
                replyIntent.putExtra("provider", provider)
                replyIntent.putExtra("quantity", quantity)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }
}