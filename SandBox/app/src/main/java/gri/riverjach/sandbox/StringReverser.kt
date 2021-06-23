package gri.riverjach.sandbox

import android.util.Log

class StringReverser(private val original: String) : Runnable {
    override fun run() {
        Log.d("StringReverser", "Thread name=${Thread.currentThread().name}")
        val reverser = original.reversed()
        Log.d(
            "StringReverser",
            "Size=${reverser.length}, Original=${original} , Reversed=${reverser}"
        )
    }
}