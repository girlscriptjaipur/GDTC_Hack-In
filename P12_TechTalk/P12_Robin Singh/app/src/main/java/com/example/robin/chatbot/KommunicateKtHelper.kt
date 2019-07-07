package com.example.robin.chatbot

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.applozic.mobicommons.ApplozicService
import io.kommunicate.KmChatBuilder
import io.kommunicate.Kommunicate
import io.kommunicate.callbacks.KMLogoutHandler
import io.kommunicate.callbacks.KmCallback
import java.lang.Exception

class KommunicateKtHelper {

    fun startNewConversation(
        context: Context?,
        agentList: List<String>?,
        botList: List<String>?,
        isSingleChat: Boolean
    ) {
        val chatBuilder = KmChatBuilder(context)
        chatBuilder.agentIds = agentList
        chatBuilder.botIds = botList
        chatBuilder.isSingleChat = isSingleChat

        val dialog = ProgressDialog(context)
        dialog.setMessage("Creating conversation, please wait...")
        dialog.setCancelable(false)
        dialog.show()

        chatBuilder.launchChat(object : KmCallback {
            override fun onSuccess(message: Any?) {
                dialog.dismiss()
            }

            override fun onFailure(error: Any?) {
                dialog.dismiss()
                Toast.makeText(context, "Error : $error", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun performLogout(context: Context?) {
        val dialog = ProgressDialog(context)
        dialog.setMessage("Logging out, please wait...")
        dialog.setCancelable(false)
        dialog.show()

        Kommunicate.logout(context, object : KMLogoutHandler {
            override fun onSuccess(context: Context?) {
                dialog.dismiss()
                Toast.makeText(
                    context,
                    ApplozicService.getContext(context).getString(com.applozic.mobicomkit.uiwidgets.R.string.user_logout_info),
                    Toast.LENGTH_SHORT
                ).show()
                try {
                    var intent = Intent(context, LoginActivity::class.java)
                    intent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
                    context?.startActivity(intent)
                    (context as Activity).finish()
                } catch (e: ClassNotFoundException) {
                    e.printStackTrace()
                }

            }

            override fun onFailure(exception: Exception?) {
                dialog.dismiss()
            }
        })
    }
}