package com.example.testteen.data.repository

import com.example.testteen.data.common.HandleResponse
import com.example.testteen.data.common.Resource
import com.example.testteen.data.mapper.base.asResource
import com.example.testteen.data.mapper.model.toDomain
import com.example.testteen.data.service.AccountNumberService
import com.example.testteen.domain.model.GetCards
import com.example.testteen.domain.repository.AccountNumberRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountNumberRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val accountNumberService: AccountNumberService
): AccountNumberRepository {
    override suspend fun getAccNumber(accNumber: String): Flow<Resource<GetCards>> {
        return handleResponse.safeApiCall {
            accountNumberService.getAccountNumber(accNumber)
        }.asResource {
            it.toDomain()
        }
    }
}