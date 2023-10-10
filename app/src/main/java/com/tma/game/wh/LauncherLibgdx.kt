package com.tma.game.wh

import android.content.Context
import android.content.Intent
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration

class LauncherLibgdx : AndroidApplication() {
    override fun onCreate(savedInstanceState: android.os.Bundle?) {
        super.onCreate(savedInstanceState)
        var config = AndroidApplicationConfiguration()
        config.useAccelerometer = false;
        config.useCompass = false;
        initialize(Main(this), config)
    }

    //add intent
    companion object {
        fun go(context: Context) {
            context.startActivity(Intent(context, LauncherLibgdx::class.java))
        }
    }

    override fun onBackPressed() {
        finishAffinity()
    }
}