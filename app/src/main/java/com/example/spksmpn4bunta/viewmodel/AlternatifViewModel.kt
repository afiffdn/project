package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.datastore.Data
import com.example.spksmpn4bunta.model.Resource
import com.example.spksmpn4bunta.model.Users
import com.example.spksmpn4bunta.model.alternatif.PostAlternatifReq
import com.example.spksmpn4bunta.model.alternatif.PostAlternatifResp
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.datadiri.DataDiriResp
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlternatifViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val _postNilai = MutableLiveData<Resource<PostAlternatifResp>> ()
    val postNilai: LiveData<Resource<PostAlternatifResp>> get()=  _postNilai

    private val _putDataDiri = MutableLiveData<Resource<DataDiriResp>>()
    val putDataDiri : LiveData<Resource<DataDiriResp>> get()= _putDataDiri

    private val _userPref = MutableLiveData<Data>()
    val userPref : LiveData<Data> get() =_userPref

    private val _getToken = MutableLiveData<Users>()
    val getToken: LiveData<Users> get() = _getToken


    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect(){
                _userPref.postValue(it)
            }
        }
    }

    fun postNilai(token: String, requestBody: PostAlternatifReq) {
        viewModelScope.launch {
            _postNilai.postValue(Resource.loading())
            try {
                _postNilai.postValue(Resource.success(repository.postNilai(token, requestBody)))
            } catch (exception: Exception) {
                _postNilai.postValue(Resource.error(exception.message ?: "Error"))
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