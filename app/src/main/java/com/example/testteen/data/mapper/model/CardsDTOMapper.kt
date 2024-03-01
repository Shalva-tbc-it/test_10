package com.example.testteen.data.mapper.model

import com.example.testteen.data.model.CardsDTO
import com.example.testteen.domain.model.GetCards

fun CardsDTO.toDomain() =
    GetCards(
        id = id,
        accountName = accountName,
        accountNumber = accountNumber,
        valueType = valueType,
        cardType = cardType,
        balance = balance,
        cardLogo = cardLogo

    )