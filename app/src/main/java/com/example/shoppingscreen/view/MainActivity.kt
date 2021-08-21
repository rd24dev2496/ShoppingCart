package com.example.shoppingscreen.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.shoppingscreen.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button=findViewById<Button>(R.id.button)
       button.setOnClickListener(View.OnClickListener {
           val intent = Intent(this, GroceryListActivity::class.java);
           startActivity(intent);

       })

    }

}