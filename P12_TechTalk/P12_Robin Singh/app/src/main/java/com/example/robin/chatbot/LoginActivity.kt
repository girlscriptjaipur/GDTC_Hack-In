package com.example.robin.chatbot

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.applozic.mobicomkit.api.account.register.RegistrationResponse
import com.applozic.mobicommons.json.GsonUtils
import com.example.robin.chatbot.databinding.ActivityLoginBinding
import io.kommunicate.Kommunicate
import io.kommunicate.callbacks.KMLoginHandler
import io.kommunicate.users.KMUser
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    val APP_ID = "303e171f353e881307b7ffac5e7a457bc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityLoginBinding>(this, R.layout.activity_login)

        Kommunicate.init(this, APP_ID)


        binding.login.setOnClickListener {

            loginUser(Kommunicate.getVisitor(), binding.loading)

//            val userEmail = binding.email.toString()
//            val password = binding.password.toString()

//            when {
//                TextUtils.isEmpty(userEmail) -> Toast.makeText(this, "Email field cannot be empty", Toast.LENGTH_SHORT).show()
//                TextUtils.isEmpty(password) -> Toast.makeText(this, "Password field cannot be empty", Toast.LENGTH_SHORT).show()
//                else -> {
//                    val kmUser = KMUser()
//                    kmUser.userId = userEmail
//                    kmUser.password = password
//
//                    loginUser(Kommunicate.getVisitor(), binding.loading)
//                }
//            }
        }

    }

    private fun loginUser(kmUser: KMUser, loading: ProgressBar) {
        loading.visibility = View.VISIBLE

        Kommunicate.login(this, kmUser, object : KMLoginHandler {

            override fun onSuccess(
                registrationResponse: RegistrationResponse?,
                context: Context?
            ) {
                loading.visibility = View.INVISIBLE
                Kommunicate.openConversation(this@LoginActivity, null)
                finish()
            }


            override fun onFailure(
                registrationResponse: RegistrationResponse?,
                exception: Exception?
            ) {
                loading.visibility = View.INVISIBLE

                var errorText = "Some error occurred"
                if (registrationResponse != null) {
                    errorText = GsonUtils.getJsonFromObject(registrationResponse, registrationResponse.javaClass)
                } else if (exception != null) {
                    errorText = GsonUtils.getJsonFromObject(exception, exception.javaClass)
                }

                Toast.makeText(this@LoginActivity, errorText, Toast.LENGTH_SHORT).show()
            }
        })
    }

}
