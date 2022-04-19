package com.github.eliascoelho911.meudiario.data.registry

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistryDao {
    @Query("SELECT * FROM registry")
    fun getAll(): Flow<List<RegistryDTO>>

    @Insert
    fun save(registryDao: RegistryDao)
}