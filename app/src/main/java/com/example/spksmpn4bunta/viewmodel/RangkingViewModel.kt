package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.datastore.Data
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.rangking.GetNilaiSawRespItem
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RangkingViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _getSaw : MutableLiveData<Resource<List<GetNilaiSawRespItem>>> = MutableLiveData()
    val getSaw : LiveData<Resource<List<GetNilaiSawRespItem>>> get() = _getSaw

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken

    private val _userPref = MutableLiveData<Data>()
    val userPref : LiveData<Data> get() =_userPref

    fun getNilaiSaw(token: String){
        viewModelScope.launch {
            _getSaw.postValue(Resource.loading())
            try {
                _getSaw.postValue(Resource.success(repository.getNilaiSaw(token)))
            }catch (e: Exception){
                _getSaw.postValue(Resource.error(e.localizedMessage?: "Error Occured"))
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

    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect(){
                _userPref.postValue(it)
            }
        }
    }
}