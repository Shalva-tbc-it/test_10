package com.example.testteen.domain.repository

import com.example.testteen.data.common.Resource
import com.example.testteen.domain.model.GetCards
import kotlinx.coroutines.flow.Flow

interface CardsRepository {
    suspend fun getCards() : Flow<Resource<List<GetCards>>>
}