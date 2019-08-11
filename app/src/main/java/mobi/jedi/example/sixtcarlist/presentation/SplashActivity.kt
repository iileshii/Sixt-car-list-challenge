package mobi.jedi.example.sixtcarlist.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            startNextScreen()
        }
    }

    private fun startNextScreen() {
        MainActivity.start(context = this)
    }
}