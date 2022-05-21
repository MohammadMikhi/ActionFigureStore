package com.example.assignment3

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var total : Double=0.0
        var names: String=""
        val hulk = findViewById<ImageButton>(R.id.imageButtonHulk)
        val spider = findViewById<ImageButton>(R.id.imageButtonSpider)
        val iron = findViewById<ImageButton>(R.id.imageButtonIron)
        val strange = findViewById<ImageButton>(R.id.imageButtonStrange)
        hulk.setOnClickListener {
            total+=14.99
            names+="1x Hulk Figure\n"
            Toast.makeText(this, "Hulk Added", Toast.LENGTH_SHORT).show()
        }
        spider.setOnClickListener {
            total+=12.99
            names+="1x Spider Man Figure\n"
            Toast.makeText(this, "Spider Man Added", Toast.LENGTH_SHORT).show()
        }
        iron.setOnClickListener {
            total+=9.99
            names+="1x Iron Man Figure\n"
            Toast.makeText(this, "Iron Man Added", Toast.LENGTH_SHORT).show()
        }
        strange.setOnClickListener {
            total+=10.99
            names+="1x Dr. Strange Figure\n"
            Toast.makeText(this, "Dr. Strange Added", Toast.LENGTH_SHORT).show()
        }

        val checkout : Button =findViewById(R.id.buttonCheckOut)
        checkout.setOnClickListener {
            val intent = Intent(this, CheckOut::class.java)
            intent.putExtra("sum",total.toString())
            intent.putExtra("names",names)
            startActivity(intent)
        }
    }
}