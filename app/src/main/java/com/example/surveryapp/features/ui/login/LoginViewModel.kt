package com.example.surveryapp.features.ui.login

import android.content.SharedPreferences
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surveryapp.features.data.model.UserRepository
import com.example.surveryapp.persistance.entities.User
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @ViewModelInject constructor(
    val userRepository: UserRepository,
    val sharedPreferences: SharedPreferences
) : ViewModel() {
    val userLoggedInLiveData = MutableLiveData<User>()
    val isLoggedIn = MutableLiveData<Boolean>()

    init {
        isLoggedIn.postValue(sharedPreferences.getBoolean("logged_in", false))
    }

    fun loginUser(username: String, password: String) {
        viewModelScope.launch {
            val user = userRepository.loginUser(username, password)
            if (user != null) {
                sharedPreferences.edit().apply {
                    putBoolean("logged_in", true)
                    putInt("user_id", user.id)
                    putString("user_name", user.username)
                    apply()
                }
            }
            userLoggedInLiveData.value = user

        }
    }
}