package com.github.eliascoelho911.meudiario.domain.repositories

import com.github.eliascoelho911.meudiario.domain.entities.Registry
import kotlinx.coroutines.flow.Flow

interface RegistryRepository {
    fun getAll(): Flow<List<Registry>>
}