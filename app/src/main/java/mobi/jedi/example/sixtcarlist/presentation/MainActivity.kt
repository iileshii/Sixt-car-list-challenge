package mobi.jedi.example.sixtcarlist.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {

        fun start(context: Context) {
            Intent(context, MainActivity::class.java)
                .run(context::startActivity)

            (context as? Activity)
                ?.run {
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    finish()
                }
        }
    }
}
