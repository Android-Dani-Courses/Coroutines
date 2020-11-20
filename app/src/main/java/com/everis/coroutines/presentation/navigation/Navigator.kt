package com.everis.coroutines.presentation.navigation

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.everis.coroutines.R

class Navigator(app: Application) : Application.ActivityLifecycleCallbacks {
    private var activity: AppCompatActivity? = null
    private val navController: NavController?
        get() = activity?.supportFragmentManager?.primaryNavigationFragment?.findNavController()

    init {
        app.registerActivityLifecycleCallbacks(this)
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        this.activity = activity as? AppCompatActivity
    }

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {
        this.activity = activity as? AppCompatActivity
    }

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        if (this.activity === activity) {
            this.activity = null
        }
    }

    fun goTo(directions: NavDirections) {
        navController?.navigate(
            directions,
            NavOptions.Builder()
                .setEnterAnim(android.R.anim.fade_in)
                .setExitAnim(android.R.anim.fade_out)
                .setPopEnterAnim(android.R.anim.fade_in)
                .setPopExitAnim(android.R.anim.fade_out)
                .build()
        )
    }

}