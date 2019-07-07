package com.example.robin.chatbot

import android.app.Application
import android.content.Context
import com.applozic.mobicomkit.api.account.user.User
import com.applozic.mobicomkit.uiwidgets.uilistener.KmActionCallback
import io.kommunicate.KmHelper
import io.kommunicate.Kommunicate

class KommunicateKTApplication : Application(), KmActionCallback {

    override fun onReceive(context: Context?, `object`: Any?, action: String?) {
        when (action) {

            Kommunicate.START_NEW_CHAT -> try {
                KommunicateKtHelper().startNewConversation(context, null, null, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            Kommunicate.LOGOUT_CALL -> KommunicateKtHelper().performLogout(context)

            Kommunicate.PRECHAT_LOGIN_CALL -> KmHelper.performLogin(context, `object` as User)
        }
    }
}