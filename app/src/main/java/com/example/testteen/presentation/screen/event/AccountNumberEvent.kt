package com.example.testteen.presentation.screen.event

sealed class AccountNumberEvent {

    data class GetCurrentAcc(val accNumber: String) : AccountNumberEvent()

    data object ResetErrorMessage : CardsEvent()

}