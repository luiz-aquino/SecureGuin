package com.luizaquino.segureguin.secureguin

import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.iid.FirebaseInstanceIdService
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging


class MyFirebaseInstanceIdService: FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Log.d("info", "Refreshed token: " + refreshedToken!!)
        FirebaseMessaging.getInstance().subscribeToTopic("news")
        FirebaseApp.initializeApp(this)
        super.onTokenRefresh()
    }
}