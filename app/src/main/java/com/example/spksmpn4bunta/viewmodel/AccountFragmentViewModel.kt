package com.example.spksmpn4bunta.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spksmpn4bunta.datastore.Data
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class AccountFragmentViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _userPref = MutableLiveData<Data>()
    val userPref : LiveData<Data> get() =_userPref


    fun getUserPref(){
        viewModelScope.launch {
            repository.getUserPref().collect(){
                _userPref.postValue(it)
            }
        }
    }
}