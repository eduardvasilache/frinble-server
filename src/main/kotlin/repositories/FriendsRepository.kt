package repositories

import io.vertx.ext.asyncsql.AsyncSQLClient
import javax.inject.Singleton

@Singleton
class FriendsRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

}