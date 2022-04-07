package ru.harlion.psy


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.harlion.psy.ui.MainFragment
import ru.harlion.psy.utils.replaceFragment

class AppActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(MainFragment(), false)
    }
}