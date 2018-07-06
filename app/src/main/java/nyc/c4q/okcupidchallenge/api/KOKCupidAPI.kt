package nyc.c4q.okcupidchallenge.api

import nyc.c4q.okcupidchallenge.model.KAPIResponse
import retrofit2.Call
import retrofit2.http.GET

interface KOKCupidAPI {

    /*@GET("/matchSample.json")
    fun getUsers(): Call<KAPIResponse>*/

    @GET("/matchSample.json")
    fun getUsers():Call<KAPIResponse>

}