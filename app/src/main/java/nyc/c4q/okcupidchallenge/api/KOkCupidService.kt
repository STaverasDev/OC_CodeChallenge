package nyc.c4q.okcupidchallenge.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class KOkCupidService {

    private val BASE_STRING: String = "https://www.okcupid.com"
    val retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
                .baseUrl(BASE_STRING)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    }

    fun getOkcupidAPI(): KOKCupidAPI {
        return retrofit.create(KOKCupidAPI::class.java)
    }

}



