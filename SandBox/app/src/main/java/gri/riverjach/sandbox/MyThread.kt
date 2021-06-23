package gri.riverjach.sandbox

import android.util.Log

class MyThread : Thread() {
    override fun run() {
        Log.d("MainActivity", "Beginning of MyThread")
        var sum = 0L
        for (i in 1..2000000000) {
            sum += i
        }
        Log.d("MainActivity", "sum=$sum")
    }
}