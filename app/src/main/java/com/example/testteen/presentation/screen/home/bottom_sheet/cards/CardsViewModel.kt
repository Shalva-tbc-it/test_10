package com.example.testteen.presentation.screen.home.bottom_sheet.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testteen.data.common.Resource
import com.example.testteen.domain.usecase.GetCardsUseCase
import com.example.testteen.presentation.mapper.toPresentation
import com.example.testteen.presentation.model.Cards
import com.example.testteen.presentation.screen.event.CardsEvent
import com.example.testteen.presentation.screen.state.CardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardsViewModel @Inject constructor(
    private val getCardsUseCase: GetCardsUseCase
): ViewModel() {

    private val _cardsState = MutableStateFlow(CardsState<Cards>())
    val cardsState: SharedFlow<CardsState<Cards>> = _cardsState.asStateFlow()


    fun onEvent(event: CardsEvent) {
        when (event) {
            is CardsEvent.GetCards -> getCards()
            is CardsEvent.ResetErrorMessage ->updateErrorMessage(message = null)
            else -> {}
        }
    }

    private fun getCards() {
        viewModelScope.launch {
            getCardsUseCase.invoke().collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _cardsState.update { state ->
                            state.copy(loading = resource.loading)
                        }
                    }
                    is Resource.Success -> {
                        _cardsState.update { state ->
                            state.copy(data = resource.data.map { it.toPresentation() })
                        }
                    }
                    is Resource.Error -> {
                        updateErrorMessage(message = resource.errorMessage)
                    }
                }
            }
        }
    }

    private fun updateErrorMessage(message: String?) {
        _cardsState.update { it.copy(errorMessage = message) }
    }

}