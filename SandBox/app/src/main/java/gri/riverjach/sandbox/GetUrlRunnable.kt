package gri.riverjach.sandbox

import android.os.Handler
import android.os.SystemClock
import android.util.Log
import java.net.URL
import java.nio.charset.Charset

data class UrlResult(val content: String, val requestDuration: Long)

class GetUrlRunnable(private val handler: Handler, private val url: String) : Runnable {
    companion object {
        const val CODE_STARTED = 1
        const val CODE_FINISH = 2
    }

    override fun run() {
        Thread.sleep(1000)
        handler.sendEmptyMessage(CODE_STARTED)

        Thread.sleep(500)


        val start = SystemClock.elapsedRealtime()

        URL(url).openStream().use { stream ->
            val content = String(
                stream.readBytes(),
                Charset.forName("UTF-8")
            )
            val duration = SystemClock.elapsedRealtime() - start
            handler.sendMessage(
                handler.obtainMessage(
                    CODE_FINISH,
                    UrlResult(
                        content = content,
                        requestDuration = duration
                    )
                )
            )
            Log.d("GetUrlRunnable", "duration : $duration")
        }

    }
}