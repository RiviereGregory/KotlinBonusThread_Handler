package gri.riverjach.sandbox

import android.util.Log

class MyRunnable : Runnable {
    override fun run() {
        Log.d("MyRunnable", "Beginning of MyRunnable")
        Log.d(
            "MyRunnable",
            "Thread id=${Thread.currentThread().id}, name=${Thread.currentThread().name}"
        )

        var sum = 0L
        for (i in 1..2000000000) {
            sum += i
        }
        Log.d("MyRunnable", "sum=$sum")
    }

}