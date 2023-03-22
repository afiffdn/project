package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.detail.GetDetailSiswaItem
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: Repository) :ViewModel() {
    private val _getDetail : MutableLiveData<Resource<GetDetailSiswaItem>> = MutableLiveData()
    val getDetail : LiveData<Resource<GetDetailSiswaItem>> get()= _getDetail

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken


    fun getDetail(token: String,id:Int){
        viewModelScope.launch {
            _getDetail.postValue(Resource.loading())
            try {
                _getDetail.postValue(Resource.success(repository.getDetail(token,id)))
            }catch (e: Exception){
                _getDetail.postValue(Resource.error(e.localizedMessage?: "Error Occured"))
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