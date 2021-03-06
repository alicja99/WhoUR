package com.put.tsm.whour

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.put.tsm.whour.data.repository.datastore.QuizDataStore
import com.put.tsm.whour.ui.utils.addInterstitialCallbacks
import com.put.tsm.whour.ui.utils.loadInterstitial
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var prefs: QuizDataStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        CoroutineScope(Dispatchers.IO).launch {
            prefs.init()
            MobileAds.initialize(applicationContext)
        }

        CoroutineScope(Dispatchers.Main).launch {
            loadInterstitial()
            addInterstitialCallbacks()
        }
    }
}