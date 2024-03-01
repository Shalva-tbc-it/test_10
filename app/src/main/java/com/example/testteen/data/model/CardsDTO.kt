package com.example.testteen.data.model

import com.squareup.moshi.Json

data class CardsDTO(
    @Json(name = "id")
    val id : Int,
    @Json(name = "account_name")
    val accountName : String,
    @Json(name = "account_number")
    val accountNumber : String,
    @Json(name = "valute_type")
    val valueType : String,
    @Json(name = "card_type")
    val cardType : String,
    @Json(name = "balance")
    val balance : Int? = null,
    @Json(name = "card_logo")
    val cardLogo : String?,

)
