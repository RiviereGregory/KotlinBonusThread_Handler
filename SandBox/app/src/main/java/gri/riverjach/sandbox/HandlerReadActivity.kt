package gri.riverjach.sandbox

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HandlerReadActivity : AppCompatActivity() {
    private val handle = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            //Log.d("HandlerReadActivity", "got message: ${msg.what}")
            val statusTextView = findViewById(R.id.statusTextView) as TextView
            val contentTextView = findViewById(R.id.contentTextView) as TextView
            val progressBar = findViewById(R.id.progressBar) as ProgressBar
            val progressTextView = findViewById(R.id.progressTextView) as TextView
            when (msg.what) {
                ReaderRunnable.CODE_STARTED -> {
                    statusTextView.text = "Le téléchargement vient de commencer"
                    progressBar.progress = 0
                    progressTextView.text = "0 %"
                }
                ReaderRunnable.CODE_PROGRESS -> {
                    val result = msg.obj as ReadResult
                    statusTextView.text = "Le téléchargement est en cours"
                    contentTextView.text = result.content
                    progressBar.progress = result.progress
                    progressTextView.text = "${result.progress} %"
                }
                ReaderRunnable.CODE_FINISHED -> {
                    val result = msg.obj as ReadResult
                    statusTextView.text = "Le téléchargement est terminé"
                    progressTextView.text = "100 %"
                    progressBar.progress = result.progress
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_read)

        val thread = Thread(ReaderRunnable(handle))
        thread.start()
    }
}