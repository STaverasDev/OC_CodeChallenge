package nyc.c4q.okcupidchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import nyc.c4q.okcupidchallenge.api.KOkCupidService
import nyc.c4q.okcupidchallenge.model.KAPIResponse
import nyc.c4q.okcupidchallenge.model.KUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KSearchViewModel(var service: KOkCupidService? = null, var userList: MutableList<KUser>? = null) : ViewModel() {

    val userLiveData: MutableLiveData<MutableList<KUser>> = MutableLiveData()

    init {
        initialize()
    }

    fun initialize() {
        getUsers()
    }


    fun getUsers() {
        val call = service?.getOkcupidAPI()?.getUsers()

        call?.enqueue(object : Callback<KAPIResponse> {

            override fun onFailure(call: Call<KAPIResponse>?, t: Throwable?) {}
            override fun onResponse(call: Call<KAPIResponse>?, response: Response<KAPIResponse>?) {
                userList = response?.body()?.userList.orEmpty().toMutableList()
                userList?.let { list ->
                    userLiveData.postValue(list)
                }
            }
        })
    }


    override fun onCleared() {
        super.onCleared()
    }
}