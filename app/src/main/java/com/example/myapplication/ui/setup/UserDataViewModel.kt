package com.example.myapplication.ui.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class UserData(val username: String?, val password: String?, val email: String?, val phoneNumber: String?)
class UserDataViewModel : ViewModel() {

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    fun setUserData(username: String, password: String, email: String, phoneNumber: String) {
        _userData.value = UserData(username, password, email, phoneNumber)
    }

    fun getUserData(): UserData? {
        return _userData.value
    }
}
