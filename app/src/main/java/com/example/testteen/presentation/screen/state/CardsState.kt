package com.example.testteen.presentation.screen.state

data class CardsState<D>(
    val loading: Boolean = false,
    val data: List<D>? = null,
    val currentData: D? = null,
    val errorMessage: String? = null
)