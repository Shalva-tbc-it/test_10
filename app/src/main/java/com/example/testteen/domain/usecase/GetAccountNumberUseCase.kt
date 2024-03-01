package com.example.testteen.domain.usecase

import com.example.testteen.domain.repository.AccountNumberRepository
import javax.inject.Inject

class GetAccountNumberUseCase @Inject constructor(
    private val accountNumberRepository: AccountNumberRepository
){

    suspend operator fun invoke(accNumber: String) = accountNumberRepository.getAccNumber(accNumber = accNumber)

}