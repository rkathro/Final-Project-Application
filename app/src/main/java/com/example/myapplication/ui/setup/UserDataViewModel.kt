package com.example.myapplication.ui.setup

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class UserData(
    val username: String?,
    val password: String?,
    val email: String?,
    val phoneNumber: String?
)

data class CompanyData(
    val logoDrawableId: Int,
    val companyName: String,
    val companyPassword: String
)
class UserDataViewModel : ViewModel() {

    private val _userData = MutableLiveData<UserData>()
    val userData: LiveData<UserData> = _userData

    private val _companyDataList = MutableLiveData<MutableList<CompanyData>>(mutableListOf())
    val companyDataList: LiveData<MutableList<CompanyData>> = _companyDataList

    private val _userDataList = MutableLiveData<MutableList<CompanyData>>(mutableListOf())
    val userDataList: LiveData<MutableList<CompanyData>> = _userDataList

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
    fun addCompanyData(companyData: CompanyData) {
        _companyDataList.value?.add(companyData)
        _companyDataList.notifyObserver()
    }

    fun removeCompanyData(companyData: CompanyData) {
        _companyDataList.value?.remove(companyData)
        _companyDataList.notifyObserver()
    }

    fun addUserCompanyData(companyData: CompanyData) {
        _userDataList.value?.add(companyData)
        _userDataList.notifyObserver()
    }

    fun removeUserCompanyData(companyData: CompanyData) {
        _userDataList.value?.remove(companyData)
        _userDataList.notifyObserver()
    }
    fun getUserDataList(): MutableList<CompanyData>? {
        return _userDataList.value
    }

    fun setUserCompanyDataList(companyDataList: MutableList<CompanyData>) {
        _userDataList.value = companyDataList.toMutableList()
        _userDataList.notifyObserver()
    }

    fun getCompanyDataList(): MutableList<CompanyData>? {
        return _companyDataList.value
    }

    fun setCompanyDataList(companyDataList: MutableList<CompanyData>) {
        _companyDataList.value = companyDataList.toMutableList()
        _companyDataList.notifyObserver()
    }

    private fun <T> MutableLiveData<T>.notifyObserver() {
        this.value = this.value
    }
}
