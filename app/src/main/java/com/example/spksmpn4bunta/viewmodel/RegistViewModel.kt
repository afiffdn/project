package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.auth.LoginResp
import com.example.spksmpn4bunta.model.auth.RegistResp
import com.example.spksmpn4bunta.model.auth.RegistReq
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistViewModel @Inject constructor(private val repository:Repository) : ViewModel() {
    private val _userRegist = MutableLiveData<Resource<RegistResp>>()
    val userRegister: LiveData<Resource<RegistResp>>
        get() = _userRegist

    fun registPost(requestBody : RegistReq) {
        viewModelScope.launch {
            _userRegist.postValue(Resource.loading())
            try {
                _userRegist.postValue(Resource.success(repository.postRegist(requestBody)))
            } catch (exception: Exception) {
                _userRegist.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }

}