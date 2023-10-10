package com.tma.game

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tma.game.wh.LauncherLibgdx

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        launchLibgdx()

        if (getNetworkInfo() != null && getNetworkInfo()!!.isConnected) {
            if (SP.getIsApp(this) != null) {
                if (SP.getIsApp(this) == "true") {
                    goLibgdx()
                } else {
                    goCoinActivity()
                }
            } else {
                goCoinActivity()
                SP.saveIsApp(this, false)
            }
        } else {
            goLibgdx()
            SP.saveIsApp(this, true)
        }
    }

    private fun goLibgdx() {
        LauncherLibgdx.go(this)
    }

    private fun goCoinActivity() {
        CoinActivity.go(this)
    }

    fun getNetworkInfo(): NetworkInfo? {
        val connectivityManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo
    }
}