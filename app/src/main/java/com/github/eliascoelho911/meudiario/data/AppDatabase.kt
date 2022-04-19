package com.github.eliascoelho911.meudiario.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.eliascoelho911.meudiario.data.registry.RegistryDTO
import com.github.eliascoelho911.meudiario.data.registry.RegistryDao

@Database(entities = [RegistryDTO::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun registryDao(): RegistryDao
}