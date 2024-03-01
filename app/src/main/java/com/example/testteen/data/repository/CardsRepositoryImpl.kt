package com.example.testteen.data.repository

import com.example.testteen.data.common.HandleResponse
import com.example.testteen.data.common.Resource
import com.example.testteen.data.mapper.base.asResource
import com.example.testteen.data.mapper.model.toDomain
import com.example.testteen.data.service.CardsService
import com.example.testteen.domain.model.GetCards
import com.example.testteen.domain.repository.CardsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardsRepositoryImpl @Inject constructor(
    private val handleResponse: HandleResponse,
    private val cardsService: CardsService
) : CardsRepository {

    override suspend fun getCards(): Flow<Resource<List<GetCards>>> {
        return handleResponse.safeApiCall {
            cardsService.getCard()
        }.asResource { cardsDTOS ->
            cardsDTOS.map {
                it.toDomain()
            }
        }
    }

}