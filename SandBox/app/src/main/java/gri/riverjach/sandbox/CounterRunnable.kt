package gri.riverjach.sandbox

import android.util.Log

class CounterRunnable : Runnable {

    private var counter = 0

    override fun run() {
        synchronized(this) {
            increment()
            Log.d(
                "CounterRunnable",
                "Thread=${Thread.currentThread().name}, value after increment = ${getValue()}"
            )

            decrement()
            Log.d(
                "CounterRunnable",
                "Thread=${Thread.currentThread().name}, value after decrement = ${getValue()}"
            )
        }
    }

    fun increment() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            // No thing
        }
        counter++
    }

    fun decrement() {
        counter--
    }

    fun getValue() = counter
}