package com.example.surveryapp.features.ui.login

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.viewModels
import com.example.surveryapp.R
import com.example.surveryapp.features.ui.dashboard.DashboardActivity
import com.example.surveryapp.features.ui.registration.ResgistrationActivity
import com.example.surveryapp.persistance.entities.User
import com.example.surveryapp.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.view.*
import kotlinx.android.synthetic.main.toolbar_layout.*
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {


    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setUpToolbar()
        loginViewModel.isLoggedIn.observe(this, object : Observer<Boolean> {
            override fun onChanged(logged_in: Boolean) {
                if (logged_in) {
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    finish()
                }
            }

        })
        loginViewModel.userLoggedInLiveData.observe(this, object : Observer<User> {
            override fun onChanged(user: User?) {
                if (user != null) {
                    showToast(getString(R.string.logged_in_successfully))
                    startActivity(Intent(this@LoginActivity, DashboardActivity::class.java))
                    finish()

                } else {
                    showToast(getString(R.string.login_failed))

                }
            }

        })

        login.setOnClickListener {
            if (validateFields()) {
                loginViewModel.loginUser(username.text.toString(), password.text.toString())
            }


        }

        sign_up.setOnClickListener {
            val intent = Intent(this, ResgistrationActivity::class.java)
            startActivity(intent)
        }


    }

    private fun validateFields(): Boolean {
        if (username.text.toString().isEmpty()) {
            showToast("Please enter username")
            return false
        }
        if (password.text.toString().isEmpty()) {
            showToast("Please enter password")
            return false
        }

        return true

    }
    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle("Login Activity")
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }

}