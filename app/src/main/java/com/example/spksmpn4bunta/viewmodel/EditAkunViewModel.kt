package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.auth.ChangeRegistReq
import com.example.spksmpn4bunta.model.auth.ChangeRegistResp
import com.example.spksmpn4bunta.model.datadiri.DataDiriResp
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditAkunViewModel  @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _putRegist = MutableLiveData<Resource<ChangeRegistResp>>()
    val putRegist : LiveData<Resource<ChangeRegistResp>> get()= _putRegist

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken

    fun putRegist(token: String,requestBody : ChangeRegistReq) {
        viewModelScope.launch {
            _putRegist.postValue(Resource.loading())
            try {
                _putRegist.postValue(Resource.success(repository.putRegist(token,requestBody)))
            } catch (exception: Exception) {
                _putRegist.postValue(Resource.error(exception.message ?: "Error"))
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