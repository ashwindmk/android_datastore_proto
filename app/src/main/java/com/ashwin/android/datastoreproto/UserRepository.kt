package com.ashwin.android.datastoreproto

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

private const val USER_STORE_FILE_NAME = "user_data_store.pb"

private val Context.userStore: DataStore<User> by dataStore(
    fileName = USER_STORE_FILE_NAME,
    serializer = UserSerializer
)

class UserRepository(private val context: Context) {
    val getUser: Flow<User> = context.userStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e("datastore-proto", "Error reading user data store", exception)
                emit(User.getDefaultInstance())
            } else {
                throw exception
            }
        }

    suspend fun updateUser(id: Long, name: String, age: Int) {
        context.userStore.updateData {
            it.toBuilder()
                .setId(id)
                .setName(name)
                .setAge(age)
                .build()
        }
    }

    suspend fun updateUserName(name: String) {
        context.userStore.updateData {
            it.toBuilder()
                .setName(name)
                .build()
        }
    }
}
