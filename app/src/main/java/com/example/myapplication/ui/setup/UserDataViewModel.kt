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
    fun setUsername(username: String) {
        _userData.value = _userData.value?.copy(username = username)
    }

    fun setPasswrod(password: String) {
        _userData.value = _userData.value?.copy(password = password)
    }

    fun setEmail(email: String) {
        _userData.value = _userData.value?.copy(email = email)
    }

    fun setPhoneNumber(phoneNumber: String) {
        _userData.value = _userData.value?.copy(phoneNumber = phoneNumber)
    }

    fun getUsername(): String? {
        return _userData.value?.username
    }

    fun getPassword(): String? {
        return _userData.value?.password
    }

    fun getEmail(): String? {
        return _userData.value?.email
    }

    fun getPhoneNumber(): String? {
        return _userData.value?.phoneNumber
    }
}
