package gri.riverjach.sandbox

import android.util.Log

class SleepRunnable : Runnable {
    private var shouldSleep = false

    override fun run() {
        Log.d("SleepRunnable", "Begin")
        var sum = 0L
        for (i in 1..10000000000) {
            sum += i

            if (i % 10L == 0L) {
                Thread.sleep(100)
            }
            if (shouldSleep) {
                shouldSleep = false
                Log.d("SleepRunnable", "About to sleep 4000ms")
                Thread.sleep(4000)
            }
            Log.d("SleepRunnable", "sum=$sum")
        }
        Log.d("SleepRunnable", "End")
    }

    fun sleep() {
        // Si on met le sleep dans la fonction c'est alors le thread
        // depuis où l'on appelle la fontion qui est mis en pause.
//        Log.d("SleepRunnable", "About to sleep 4000ms")
//        Thread.sleep(4000)

        // Il faut utiliser une variable pour mettre le bon thread en sleep
        shouldSleep = true
    }


}