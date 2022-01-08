package com.ashwin.android.datastoreproto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val userRepository: UserRepository = UserRepository(application)

    val user = userRepository.getUser.asLiveData()

    fun updateUserName(name: String) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUserName(name)
    }

    fun updateUser(id: Long, name: String, age: Int) = viewModelScope.launch(Dispatchers.IO) {
        userRepository.updateUser(id, name, age)
    }
}
