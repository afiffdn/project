package com.example.spksmpn4bunta.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.spksmpn4bunta.model.auth.LoginReq
import com.example.spksmpn4bunta.model.auth.RegistReq
import com.example.spksmpn4bunta.model.Users
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {
    companion object {
        private val DATASTORELOGIN = "login_preferences"
        private val TOKEN = stringPreferencesKey("token")
        private val USERNAME = stringPreferencesKey("username_key")
        private val PASSWORD = stringPreferencesKey("password_key")
        private val SEBAGAI = stringPreferencesKey("sebagai_key")
        private val ID = intPreferencesKey("id")
        private val Context.datastore by preferencesDataStore(name = DATASTORELOGIN)

        const val DEF_ID = -1
        const val DEF_TOKEN = "def_token"
        const val DEF_USERNAME = "default_username"
        const val DEF_PASSWORD = "default_password"
        const val DEF_SEBAGAI = "default_sebagai"
    }

    suspend fun setToken(user: Users) {
        context.datastore.edit {
            it[TOKEN] =user.token
            it[ID] = user.id
        }
    }

    suspend fun getToken(): Flow<Users> {
        return context.datastore.data.map {
            Users(
                it[TOKEN] ?: DEF_TOKEN,
                it[ID] ?: DEF_ID
            )
        }
    }
    suspend fun saveUser(userData: LoginReq) {
        context.datastore.edit {
            it[USERNAME] = userData.username
            it[PASSWORD] = userData.password
            it[SEBAGAI] = userData.sebagai
        }
    }


    fun getUser(): Flow<LoginReq> {
        return context.datastore.data.map {
            LoginReq(
                it[USERNAME] ?:  DEF_USERNAME,
                it[PASSWORD] ?: DEF_PASSWORD,
                it[SEBAGAI] ?: DEF_SEBAGAI
            )
        }
    }
    suspend fun delete() {
        context.datastore.edit {
            it.clear()
        }
    }
}