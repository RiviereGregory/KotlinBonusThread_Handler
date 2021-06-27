package gri.riverjach.sandbox

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sleepButton = findViewById(R.id.sleepButton) as Button
        val stopButton = findViewById(R.id.stopButton) as Button
        Log.d("MainActivity", "onCreate ==> BEGIN ")

        val strings = listOf(
            "Kotlin",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.",
            "This is a very, very very long sentence"
        )

        for ((i, str) in strings.withIndex()) {
            val runnable1 = StringReverser(str)
            val thread = Thread(runnable1, "Reverser #${i + 1}")
            thread.start()
        }

        val runnable = SleepRunnable()
        val threadSleep = Thread(runnable)

        sleepButton.setOnClickListener {
            runnable.sleep()
        }

        stopButton.setOnClickListener {
            runnable.stop()
            // threadSleep.join va bloquer le thread pendant 100 ms en attendant
            // que le thread s'arrêtre
            threadSleep.join(100)

            // Pour stopper le thread s'il ne s'arrête pas naturellement
            threadSleep.interrupt()
        }

        threadSleep.start()

        // Bloque le thread où on est jusqu'à ce que le threadSleep (ici) soit fini
        // threadSleep.join()


        val counterRunnable = CounterRunnable()
        val t1 = Thread(counterRunnable)
        val t2 = Thread(counterRunnable)
        val t3 = Thread(counterRunnable)

        t1.start()
        t2.start()
        t3.start()

        val crawler = Crawler()
        crawler.crawl()

        Log.d("MainActivity", "onCreate ==> END ")
    }
}