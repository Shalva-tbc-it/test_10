package com.example.testteen.domain.usecase

import com.example.testteen.domain.repository.CardsRepository
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(
    private val cardsRepository: CardsRepository
) {

    suspend operator fun invoke() = cardsRepository.getCards()

}