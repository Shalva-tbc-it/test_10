package com.example.testteen.presentation.screen.event

sealed class CardsEvent {

    data object GetCards : CardsEvent()
    data object ResetErrorMessage : CardsEvent()

}