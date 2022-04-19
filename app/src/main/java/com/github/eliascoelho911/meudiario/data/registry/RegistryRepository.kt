package com.github.eliascoelho911.meudiario.data.registry

import kotlinx.coroutines.flow.Flow

interface RegistryRepository {
    fun getAll(): Flow<List<RegistryDTO>>
}