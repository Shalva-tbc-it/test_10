package com.example.testteen.data.mapper.base

import com.example.testteen.data.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

suspend fun <DTO : Any, Domain : Any> Flow<Resource<DTO>>.asResource(
    onSuccess: suspend (DTO) -> Domain,
): Flow<Resource<Domain>> {
    return this.map {
        when(it) {
            is Resource.Success -> Resource.Success(data = onSuccess.invoke(it.data))
            is Resource.Error -> Resource.Error(errorMessage = it.errorMessage)
            is Resource.Loading -> Resource.Loading(loading = it.loading)
        }
    }
}