package repositories

import io.vertx.ext.asyncsql.AsyncSQLClient
import javax.inject.Singleton

@Singleton
class ActivitiesRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

}