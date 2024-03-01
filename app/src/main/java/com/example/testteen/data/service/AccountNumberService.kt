package com.example.testteen.data.service

import com.example.testteen.data.model.CardsDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountNumberService {

    @GET("4253786f-3500-4148-9ebc-1fe7fb9dc8d5/{acc_number}")
    suspend fun getAccountNumber(@Path("acc_number") accNumber: String) : Response<CardsDTO>

}