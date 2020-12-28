package com.example.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entities.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val createUserUseCase: CreateUserUseCase, private val getUserUseCase: GetUserUseCase) : ViewModel(){
    fun onClickedLogin(emailUser: String, password:String) {
        viewModelScope.launch(Dispatchers.IO) {
           // getUserUseCase.invoke(emailUser, password)
           // createUserUseCase.invoke(User("test"))
            //delay(1000)
            val user: User? = getUserUseCase.invoke(emailUser)
            val loginStatus = if(user != null){
                LoginSuccess(user.email)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
        }
        //counter.value= (counter.value ?: 0) + 1
    }

    val loginLiveData: MutableLiveData<LoginStatus> = MutableLiveData()

}