package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.*
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.datadiri.DataDiriReq
import com.example.spksmpn4bunta.model.datadiri.DataDiriResp
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DataDiriViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val _postDataDiri = MutableLiveData<Resource<DataDiriResp>> ()
    val postDataDiri: LiveData<Resource<DataDiriResp>> get()=  _postDataDiri

    private val _putDataDiri = MutableLiveData<Resource<DataDiriResp>>()
    val putDataDiri : LiveData<Resource<DataDiriResp>> get()= _putDataDiri

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken

    private val _userPref = MutableLiveData<LoginReq>()
    val userPref : LiveData<LoginReq> get() =_userPref


    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect(){
                _userPref.postValue(it)
            }
        }
    }

    fun postDataDiri(token: String, requestBody: DataDiriReq) {
        viewModelScope.launch {
            _postDataDiri.postValue(Resource.loading())
            try {
                _postDataDiri.postValue(Resource.success(repository.postDataDiri(token, requestBody)))
            } catch (exception: Exception) {
                _postDataDiri.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }

    fun putDataDiri(token: String,requestBody : DataDiriReq) {
        viewModelScope.launch {
            _putDataDiri.postValue(Resource.loading())
            try {
                _putDataDiri.postValue(Resource.success(repository.putDataDiri(token,requestBody)))
            } catch (exception: Exception) {
                _putDataDiri.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }



    fun getToken() {
        viewModelScope.launch {
            repository.getToken().collect {
                _getToken.postValue(it)
            }
        }
    }

}