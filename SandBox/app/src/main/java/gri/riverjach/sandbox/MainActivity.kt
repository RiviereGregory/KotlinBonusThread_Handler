package gri.riverjach.sandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate ==> BEGIN ")

//        var sum = 0L
//        for (i in 1..2000000000) {
//            sum += i
//        }
//        Log.d("MainActivity", "sum=$sum")
        val runnable = MyRunnable()
        val threadRunnable = Thread(runnable, "KotlinThread")
        threadRunnable.start()

        Log.d("MainActivity", "onCreate ==> END ")
    }
}