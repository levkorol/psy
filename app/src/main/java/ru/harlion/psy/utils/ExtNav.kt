package ru.harlion.psy.utils

import android.content.Intent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import ru.harlion.psy.R


//открыть активити
fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

//из активити в фрагмент
fun AppCompatActivity.replaceFragment(fragment: Fragment, addStack: Boolean = true) {
    supportFragmentManager.replaceFragment(fragment, addStack, R.id.fragmentContainer)
}

//во фрагментах
fun Fragment.replaceFragment(fragment: Fragment, addStack: Boolean) {
    parentFragmentManager.replaceFragment(
        fragment,
        addStack,
        (view?.parent as View).id
    )
}


private fun FragmentManager.replaceFragment(
    fragment: Fragment,
    addStack: Boolean,
    containerId: Int,
) {
    beginTransaction()
        .also {
            if (addStack) {
                it.addToBackStack(null)
            }
        }
        .setCustomAnimations(
            R.anim.slide_in, // new fragment
            R.anim.fade_out, // исчезновение фрагмента

            R.anim.fade_in, // возврат появление старого
            R.anim.slide_out, // возрат исчезновение нового
        )
        .replace(containerId, fragment).commit()
}