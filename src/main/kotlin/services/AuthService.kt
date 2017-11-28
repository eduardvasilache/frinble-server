package services

import repositories.UsersRepository
import javax.inject.Singleton

@Singleton
class AuthService(private val usersRepository: UsersRepository) {

}