package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.model.*
import com.example.spksmpn4bunta.model.aktivasi.GetUsersRespItem
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivatedViewModel @Inject constructor(private val repository: Repository) : ViewModel()  {

    private val _getUsers : MutableLiveData<Resource<List<GetUsersRespItem>>> = MutableLiveData()
    val getUsers : LiveData<Resource<List<GetUsersRespItem>>> get() = _getUsers

    private val _putActivated = MutableLiveData<Resource<Message>>()
    val putActivated : LiveData<Resource<Message>> get()= _putActivated

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken

    fun getUsers(token: String){
        viewModelScope.launch {
            _getUsers.postValue(Resource.loading())
            try {
                _getUsers.postValue(Resource.success(repository.getUsers(token)))
            }catch (e: Exception){
                _getUsers.postValue(Resource.error(e.localizedMessage?: "Error Occured"))
            }
        }
    }

    fun putActivated(token: String,id:Int) {
        viewModelScope.launch {
            _putActivated.postValue(Resource.loading())
            try {
                _putActivated.postValue(Resource.success(repository.putActivated(token,id)))
            } catch (exception: Exception) {
                _putActivated.postValue(Resource.error(exception.message ?: "Error"))
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