package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.*
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.auth.LoginResp
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel  @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _userLogin = MutableLiveData<Resource<LoginResp>>()
    val userLogin: LiveData<Resource<LoginResp>>
        get() = _userLogin

    fun loginPost(requestBody : LoginReq) {
        viewModelScope.launch {
            _userLogin.postValue(Resource.loading())
            try {
                _userLogin.postValue(Resource.success(repository.postLogin(requestBody)))
            } catch (exception: Exception) {
                _userLogin.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }
    fun setUserToken(user: Users){
        viewModelScope.launch {
            repository.setDatastore(user)
        }
    }
    fun saveUserPref(userPref : LoginReq){
        viewModelScope.launch {
            repository.saveUserPref(userPref)
        }
    }

}