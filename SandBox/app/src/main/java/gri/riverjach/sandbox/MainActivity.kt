package gri.riverjach.sandbox

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val threadButton = findViewById(R.id.threadButton) as Button
        val handlerButton = findViewById(R.id.handlerButton) as Button
        val handlerDButton = findViewById(R.id.handlerDButton) as Button
        Log.d("MainActivity", "onCreate ==> BEGIN ")

        threadButton.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        handlerButton.setOnClickListener {
            val intent = Intent(this, HandlerActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        handlerDButton.setOnClickListener {
            val intent = Intent(this, HandlerDActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        Log.d("MainActivity", "onCreate ==> END ")
    }
}