package android.architecture.api.dataStore

import android.content.Context
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StateManager @Inject constructor(@ApplicationContext context: Context) {
    private val dataStore = context.createDataStore(name = "state_prefs")

    companion object {
        val DARK_MODE = preferencesKey<Boolean>(name = "dark_mode")
        val DATA = preferencesKey<String>(name = "data")
    }

    suspend fun storeData(data: String) {
        dataStore.edit {
            it[DATA] = data
        }


    }

    suspend fun isDarkMode(isDarkMode: Boolean) {
        dataStore.edit {
            it[DARK_MODE] = isDarkMode
        }
    }

    val dataFlow: Flow<String> = dataStore.data.map {
        it[DATA] ?: ""
    }

    val darkMode: Flow<Boolean> = dataStore.data.map {
        it[DARK_MODE] ?: false
    }
}