package gri.riverjach.sandbox

import android.os.Handler
import android.util.Log

class WorkerHandler(private val handler: Handler, private val input: Int) : Runnable {
    companion object {
        const val CODE_RESULT = 1
        const val CODE_FAILURE = 2
    }

    override fun run() {
        Log.d("WorkerHandler", "Begin")

        // Pour simuler un traitement
        Thread.sleep(2000)

        if (input < 0) {
            handler.sendEmptyMessage(CODE_FAILURE)
            return
        }

        val result = input * input

        // poster le résultat vers le main thread pour mettre à jour la UI
        handler.sendMessage(handler.obtainMessage(CODE_RESULT, result, 0))

        Log.d("WorkerHandler", "End")
    }
}