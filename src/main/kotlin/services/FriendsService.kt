package services

import repositories.FriendsRepository
import javax.inject.Singleton

@Singleton
class FriendsService(private val friendsRepository: FriendsRepository) {

}