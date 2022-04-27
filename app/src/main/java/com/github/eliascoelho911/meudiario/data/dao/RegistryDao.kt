package com.github.eliascoelho911.meudiario.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.eliascoelho911.meudiario.data.dto.RegistryDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface RegistryDao {
    @Query("SELECT * FROM registry")
    fun getAll(): Flow<List<RegistryDTO>>
}