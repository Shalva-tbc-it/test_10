package com.example.testteen.presentation.screen.home.bottom_sheet.account_number

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testteen.data.common.Resource
import com.example.testteen.domain.usecase.GetAccountNumberUseCase
import com.example.testteen.presentation.mapper.toPresentation
import com.example.testteen.presentation.model.Cards
import com.example.testteen.presentation.screen.event.AccountNumberEvent
import com.example.testteen.presentation.screen.state.CardsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountNumberViewModel @Inject constructor(
    private val getAccountNumberUseCase: GetAccountNumberUseCase
): ViewModel(){

    private val _currentAccState = MutableStateFlow(CardsState<Cards>())
    val currentAccState: SharedFlow<CardsState<Cards>> = _currentAccState.asStateFlow()


    fun onEvent(event: AccountNumberEvent) {
        when (event) {
            is AccountNumberEvent.GetCurrentAcc -> getCurrentAcc(event.accNumber)
            else -> {}
        }
    }

    private fun getCurrentAcc(accNumber : String) {
        viewModelScope.launch {
            getAccountNumberUseCase.invoke(accNumber = accNumber).collect { resource ->
                when (resource) {
                    is Resource.Loading -> {
                        _currentAccState.update { state ->
                            state.copy(loading = resource.loading)
                        }
                    }
                    is Resource.Success -> {
                        _currentAccState.update { state ->
                            state.copy(currentData = resource.data.toPresentation())
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
        _currentAccState.update { it.copy(errorMessage = message) }
    }


}