package com.example.testteen.domain.repository

import com.example.testteen.data.common.Resource
import com.example.testteen.domain.model.GetCards
import kotlinx.coroutines.flow.Flow

interface AccountNumberRepository {

    suspend fun getAccNumber(accNumber: String) : Flow<Resource<GetCards>>

}