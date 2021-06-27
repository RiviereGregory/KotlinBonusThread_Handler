package gri.riverjach.sandbox

import android.os.SystemClock
import android.util.Log

private val TAG = "Crawler"

class Crawler {
    val sites = mapOf(
        "https://kotlinlang.org/" to "Kotlin body",
        "https://www.google.com/" to "Google body",
        "https://play.kotlinlang.org/" to "Kotlin Playground body",
        "https://twitter.com/" to "Twitter body"
    )
    private val results = mutableListOf<String>()

    fun crawl() {
        val start = SystemClock.elapsedRealtime()
        Log.i(TAG, "Crawl started")

        val threads = mutableListOf<Thread>()

        for (site in sites) {
            val worker = Worker(site, this)
            val thread = Thread(worker, "Thread-${site.key}")
            thread.start()
            threads.add(thread)
        }

        for (thread in threads) {
            thread.join()
        }

        val elapsed = SystemClock.elapsedRealtime() - start
        Log.i(TAG, "Crawl finished. Took $elapsed ms")
    }

    fun addResult(result: String) {
        synchronized(results) {
            results.add(result)
        }
    }
}

class Worker(
    private val site: Map.Entry<String, String>,
    private val crawler: Crawler
) : Runnable {
    val TAG = "Worker"
    override fun run() {
        Log.d(TAG, "Crawling site ${site.key}...")
        Thread.sleep(2000)
        crawler.addResult(site.value)
        Log.d(TAG, "Crawled finished for ${site.key}")
    }
}