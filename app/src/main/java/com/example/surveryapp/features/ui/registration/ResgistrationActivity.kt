package com.example.surveryapp.features.ui.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.surveryapp.R
import com.example.surveryapp.features.ui.login.LoginActivity
import com.example.surveryapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_resgistration.*
import kotlinx.android.synthetic.main.activity_resgistration.password
import kotlinx.android.synthetic.main.activity_resgistration.username
import kotlinx.android.synthetic.main.activity_resgistration.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import java.util.*

@AndroidEntryPoint
class ResgistrationActivity : AppCompatActivity() {
    val registrationViewModel: RegistrationViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resgistration)
        setUpToolbar()
        signup.setOnClickListener {
            if (validateFields()) {
                registrationViewModel.saveUserDetails(
                    name.text.toString(), username.text.toString(), email.text.toString(),
                    phone.text.toString(), password.text.toString()
                )
            }
        }
        registrationViewModel.savedUserId.observe(this, object : Observer<Long> {
            override fun onChanged(t: Long) {
                if (t > 0) {
                    showToast(getString(R.string.registration_success_msg))
                    startActivity(Intent(this@ResgistrationActivity, LoginActivity::class.java))
                    finish()
                } else if (t == -1L) {
                    showToast(getString(R.string.user_already_exists))

                } else {
                    showToast(getString(R.string.registration_failed))

                }
            }

        })
    }

    private fun validateFields(): Boolean {
        if (name.text.toString().isEmpty()) {
            showToast("Please enter name")
            return false
        }

        if (username.text.toString().isEmpty()) {
            showToast("Please enter username")
            return false
        }
        if (email.text.toString().isEmpty()) {
            showToast("Please enter Email")
            return false
        }
        if (phone.text.toString().isEmpty() || phone.text.toString().length < 10) {
            showToast("Please enter valid Phone number")
            return false
        }

        if (password.text.toString().isEmpty()) {
            showToast("Please enter password")
            return false
        }

        if (confirmpassword.text.toString().isEmpty() || !confirmpassword.text.toString()
                .equals(password.text.toString())
        ) {
            showToast("Passwords does not match")
            return false
        }


        return true
    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle("Sign Up")
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}