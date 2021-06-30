package gri.riverjach.sandbox

import android.graphics.Paint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

const val CODE_DELETE = 1
const val CODE_CANCEL_DELETE = 2

class HandlerDActivity : AppCompatActivity() {
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            Log.d("HandlerDActivity", "got message: ${msg.what}")
            val itemTextView = findViewById(R.id.itemTextView) as TextView
            when (msg.what) {
                CODE_DELETE -> itemTextView.visibility = View.INVISIBLE
                CODE_CANCEL_DELETE -> {
                    itemTextView.paintFlags = 0
                    removeMessages(CODE_DELETE)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_d)
        Log.d("HandleDActivity", "onCreate ==> BEGIN ")

        val deleteButton = findViewById(R.id.deleteButton) as Button
        val itemTextView = findViewById(R.id.itemTextView) as TextView

        deleteButton.setOnClickListener {
            itemTextView.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            handler.sendEmptyMessageDelayed(CODE_DELETE, 3000)
            val snackbar =
                Snackbar.make(findViewById(R.id.root), "Delete item", Snackbar.LENGTH_LONG)
            snackbar.setAction("Cancel"){
                handler.sendEmptyMessage(CODE_CANCEL_DELETE)
            }

            snackbar.show()
        }

        Log.d("HandleDActivity", "onCreate ==> END ")
    }
}