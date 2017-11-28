package services

import repositories.ActivitiesRepository
import javax.inject.Singleton

@Singleton
class ActivitiesService(private val activitiesRepository: ActivitiesRepository) {

}