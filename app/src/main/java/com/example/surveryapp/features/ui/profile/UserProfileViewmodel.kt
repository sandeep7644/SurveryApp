package com.example.surveryapp.features.ui.profile

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surveryapp.data.model.UserRepository
import com.example.surveryapp.persistance.entities.User
import kotlinx.coroutines.launch

class UserProfileViewmodel @ViewModelInject constructor(val userRepository: UserRepository) :
    ViewModel() {
    val currentUserLiveData = MutableLiveData<User>()
    init {
        viewModelScope.launch{
            val user=userRepository.getCurrentLoggedInUser()
            currentUserLiveData.postValue(user)
        }
    }

}