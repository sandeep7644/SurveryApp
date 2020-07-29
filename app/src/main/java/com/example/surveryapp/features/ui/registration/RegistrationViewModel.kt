package com.example.surveryapp.features.ui.registration

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.surveryapp.features.data.model.UserRepository
import com.example.surveryapp.persistance.entities.User
import kotlinx.coroutines.launch

class RegistrationViewModel @ViewModelInject constructor(val userRepository: UserRepository) :
    ViewModel() {
    val savedUserId = MutableLiveData<Long>()
    //    name.text.toString(),username.text.toString(),email.text.toString(),
//    phone.text.toString(),password.text.toString()
    fun saveUserDetails(
        name: String,
        username: String,
        email: String,
        phone: String,
        password: String
    ) {
        viewModelScope.launch {
            if(userRepository.checkUserExists(username)==null){
                val user_id =  userRepository.saveUserDetails(
                    User(
                        name = name,
                        username = username,
                        email = email,
                        phone = phone,
                        password = password
                    )
                )

                savedUserId.postValue(user_id)
            }
            else{
                savedUserId.postValue(-1)

            }

        }
    }
}