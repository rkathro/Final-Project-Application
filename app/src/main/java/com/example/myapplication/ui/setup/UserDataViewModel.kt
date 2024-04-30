package com.example.myapplication.ui.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserDataViewModel : ViewModel() {

    private val _username = MutableLiveData<String>()
    val username: LiveData<String> = _username

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _phoneNumber = MutableLiveData<String>()
    val phoneNumber: LiveData<String> = _phoneNumber

    fun setUserData(username: String, email: String, phoneNumber: String) {
        _username.value = username
        _email.value = email
        _phoneNumber.value = phoneNumber
    }

    fun getUserData(): Triple<String?, String?, String?> {
        return Triple(_username.value, _email.value, _phoneNumber.value)
    }
}
