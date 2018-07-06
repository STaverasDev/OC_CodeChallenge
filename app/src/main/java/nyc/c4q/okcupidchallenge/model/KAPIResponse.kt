package nyc.c4q.okcupidchallenge.model

import com.google.gson.annotations.SerializedName

data class KAPIResponse(@SerializedName("data") val userList: MutableList<KUser>)