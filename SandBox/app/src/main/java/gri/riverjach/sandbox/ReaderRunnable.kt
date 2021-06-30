package gri.riverjach.sandbox

import android.os.Handler

data class ReadResult(val content: String, val progress: Int)

class ReaderRunnable(private val handler: Handler) : Runnable {
    companion object {
        const val CODE_STARTED = 1
        const val CODE_PROGRESS = 2
        const val CODE_FINISHED = 3
    }

    override fun run() {
        handler.sendEmptyMessage(CODE_STARTED)

        Thread.sleep(1000)

        val content = StringBuffer()
        val totalSize = contentSize()
        var currentSize = 0

        while (content.length < totalSize) {
            content.append(readContent())
            currentSize = content.length

            Thread.sleep(500)
            var progress = 100.0 * (currentSize.toDouble() / totalSize)
            handler.sendMessage(
                handler.obtainMessage(
                    CODE_PROGRESS,
                    ReadResult(content.toString(), progress.toInt())
                )
            )

        }
        handler.sendMessage(
            handler.obtainMessage(
                CODE_FINISHED,
                ReadResult(content.toString(), 100)
            )
        )

    }

}
