package repositories

import helpers.query
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import models.User
import javax.inject.Singleton

@Singleton
class ActivitiesRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

}