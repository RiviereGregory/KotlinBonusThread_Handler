package gri.riverjach.sandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate ==> BEGIN ")

        val strings = listOf(
                "Kotlin",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
                "This is a very, very very long sentence"
        )

        for ((i, str) in strings.withIndex()) {
            val runnable1 = StringReverser(str)
            val thread = Thread(runnable1, "Reverser #${i + 1}")
            thread.start()
        }


        Log.d("MainActivity", "onCreate ==> END ")
    }
}