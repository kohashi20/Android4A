package com.example.android4a.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entities.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val createUserUseCase: CreateUserUseCase, private val getUserUseCase: GetUserUseCase) : ViewModel(){
    fun onClickedIncrement(emailUser: String) {
        viewModelScope.launch(Dispatchers.IO) {

            createUserUseCase.invoke(User("test"))
            //delay(1000)
            val user = getUserUseCase.invoke("test")
        }
        //counter.value= (counter.value ?: 0) + 1
    }

    val counter: MutableLiveData<Int> = MutableLiveData()
    init{
        counter.value = 0
    }
}