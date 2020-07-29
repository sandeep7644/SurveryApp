package com.example.surveryapp.features.ui.profile

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.surveryapp.R
import com.example.surveryapp.databinding.ActivityUserProfileBinding
import com.example.surveryapp.features.ui.dashboard.DashboardActivity
import com.example.surveryapp.persistance.entities.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_layout.*

@AndroidEntryPoint
class UserProfileActivity : AppCompatActivity() {
    val userProfileViewmodel: UserProfileViewmodel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityUserProfileBinding>(
            this,
            R.layout.activity_user_profile
        )
        userProfileViewmodel.currentUserLiveData.observe(this, object : Observer<User> {
            override fun onChanged(user: User) {
                binding.user = user
            }

        })
        setUpToolbar()

    }

    fun setUpToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle("User Profile")
        toolbar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}