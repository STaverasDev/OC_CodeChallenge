package nyc.c4q.okcupidchallenge.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KOkCupidService {

    private val _baseString = "https://www.okcupid.com"
    private val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(_baseString)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    fun getOkCupidAPI(): KOKCupidAPI {
        return retrofit.create(KOKCupidAPI::class.java)
    }

}



