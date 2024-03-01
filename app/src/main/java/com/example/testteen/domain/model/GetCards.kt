package com.example.testteen.domain.model

data class GetCards(
    val id: Int,
    val accountName: String,
    val accountNumber: String,
    val valueType: String,
    val cardType: String,
    val balance: Int?,
    val cardLogo: String?,

    )
