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
        val sleepButton = findViewById(R.id.threadButton) as Button
        val stopButton = findViewById(R.id.handlerButton) as Button
        Log.d("MainActivity", "onCreate ==> BEGIN ")

        sleepButton.setOnClickListener {
            val intent = Intent(this, ThreadActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        stopButton.setOnClickListener {
            val intent = Intent(this, HandlerActivity::class.java)
            intent.action = Intent.ACTION_VIEW
            startActivity(intent)
        }

        Log.d("MainActivity", "onCreate ==> END ")
    }
}