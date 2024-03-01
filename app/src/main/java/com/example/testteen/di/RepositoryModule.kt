package com.example.testteen.di

import com.example.testteen.data.common.HandleResponse
import com.example.testteen.data.repository.AccountNumberRepositoryImpl
import com.example.testteen.data.repository.CardsRepositoryImpl
import com.example.testteen.data.service.AccountNumberService
import com.example.testteen.data.service.CardsService
import com.example.testteen.domain.repository.AccountNumberRepository
import com.example.testteen.domain.repository.CardsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCardsRepository(
        handleResponse: HandleResponse,
        cardsService: CardsService
    ): CardsRepository {
        return CardsRepositoryImpl(
            handleResponse = handleResponse,
            cardsService = cardsService
        )
    }

    @Provides
    @Singleton
    fun provideAccountNumberRepository(
        handleResponse: HandleResponse,
        accountNumberService: AccountNumberService
    ): AccountNumberRepository {
        return AccountNumberRepositoryImpl(
            handleResponse = handleResponse,
            accountNumberService = accountNumberService
        )
    }

}