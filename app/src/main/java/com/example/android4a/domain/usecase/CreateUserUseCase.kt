package com.example.android4a.domain.usecase

import com.example.android4a.data.repository.UserRepository
import com.example.android4a.domain.entities.User

class CreateUserUseCase(private val userRepository: UserRepository) {
    suspend fun invoke(user: User){
    userRepository.createUser(user)
    }
}