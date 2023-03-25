package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.detail.GetDetailSiswaItem
import com.example.spksmpn4bunta.model.detail.Messagedelete
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) :ViewModel() {
    private val _getDetail : MutableLiveData<Resource<GetDetailSiswaItem>> = MutableLiveData()
    val getDetail : LiveData<Resource<GetDetailSiswaItem>> get() = _getDetail

    private val _getDetail2 : MutableLiveData<Resource<GetDetailSiswaItem>> = MutableLiveData()
    val getDetail2 : LiveData<Resource<GetDetailSiswaItem>> get() = _getDetail2

    private val _deleteDetail : MutableLiveData<Resource<Messagedelete>> = MutableLiveData()
    val deleteDetail : LiveData<Resource<Messagedelete>> get() = _deleteDetail


    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken


    fun getDetail(token: String,id:Int) {
        viewModelScope.launch {
            _getDetail.postValue(Resource.loading())
            try {
                val detail = repository.getDetail(token, id).first()
                _getDetail.postValue(Resource.success(detail))
            } catch (exception: Exception) {
                _getDetail.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }

    fun getDetail2(token: String,id:Int) {
        viewModelScope.launch {
            _getDetail.postValue(Resource.loading())
            try {
                val detail = repository.getDetail2(token, id).first()
                _getDetail.postValue(Resource.success(detail))
            } catch (exception: Exception) {
                _getDetail.postValue(Resource.error(exception.message ?: "Error"))
            }
        }
    }
    fun deleteDetail(token: String,id:Int) {
        viewModelScope.launch {
            _deleteDetail.postValue(Resource.loading())
            try {
                val detail = repository.deleteDetail(token, id).first()
                _deleteDetail.postValue(Resource.success(detail))
            } catch (exception: Exception) {
                _deleteDetail.postValue(Resource.error(exception.message ?: "Error"))
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