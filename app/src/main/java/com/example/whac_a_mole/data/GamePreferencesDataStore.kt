package com.example.whac_a_mole.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

private const val GAME_PREFERENCES_NAME = "game_preferences"

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = GAME_PREFERENCES_NAME
)

class GamePreferencesDataStore(context: Context) {

    private val BEST_SCORE = intPreferencesKey("best_score")

    suspend fun save(bestScore: Int, context: Context) {
        context.dataStore.edit { preferences ->
            preferences[BEST_SCORE] = bestScore
        }
    }

    suspend fun read(context: Context): Int? {
        val preferences = context.dataStore.data.first()
        return preferences[BEST_SCORE]
    }
}