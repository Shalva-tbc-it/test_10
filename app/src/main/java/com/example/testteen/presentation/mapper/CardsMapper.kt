package com.example.testteen.presentation.mapper

import com.example.testteen.domain.model.GetCards
import com.example.testteen.presentation.model.Cards

fun GetCards.toPresentation() =
    Cards(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valueType = valueType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo

    )