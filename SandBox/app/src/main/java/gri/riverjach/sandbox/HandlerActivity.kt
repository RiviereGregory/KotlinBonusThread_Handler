package gri.riverjach.sandbox

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HandlerActivity: AppCompatActivity() {
    private val url = "https://kotlinlang.org"

    private val handle = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.d("MainActivity", "got message: ${msg.what}")
            val resultTextView = findViewById(R.id.resultTextView) as TextView
            when (msg.what) {
                WorkerHandler.CODE_RESULT -> {
                    resultTextView.setTextColor(Color.GREEN)
                    resultTextView.text = "The result is ${msg.arg1}"
                }
                WorkerHandler.CODE_FAILURE -> {

                    resultTextView.setTextColor(Color.RED)
                    resultTextView.text = "The result is ${msg.arg1}"
                }
            }
        }
    }

    // Autre syntaxe pour le handle
    private val handlerUrl = Handler(Looper.getMainLooper()) { message ->
        val statusTextView = findViewById(R.id.statusTextView) as TextView
        val contentTextView = findViewById(R.id.contentTextView) as TextView
        when (message.what) {
            GetUrlRunnable.CODE_STARTED -> {
                statusTextView.text = "Started request on $url"
            }
            GetUrlRunnable.CODE_FINISH -> {
                val result = message.obj as UrlResult
                statusTextView.text = "Finish request"
                contentTextView.text = """
                    request duration : ${result.requestDuration} ms
                    Content : ${result.content}
                """.trimIndent()
            }
        }

        true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        val workerHandler = WorkerHandler(handle, 4)
        val threadWorkerHandler = Thread(workerHandler)
        threadWorkerHandler.start()

        val threadUrl = Thread(GetUrlRunnable(handlerUrl, url))
        threadUrl.start()
    }
}