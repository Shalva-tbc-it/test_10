package com.example.testteen.data.service

import com.example.testteen.data.model.CardsDTO
import retrofit2.Response
import retrofit2.http.GET

interface CardsService {

    @GET("5c74ec0e-5cc1-445e-b64b-b76b286b215f")
    suspend fun getCard() : Response<List<CardsDTO>>

}