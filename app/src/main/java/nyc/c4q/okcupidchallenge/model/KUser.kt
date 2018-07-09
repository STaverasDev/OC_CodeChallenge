package nyc.c4q.okcupidchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import nyc.c4q.okcupidchallenge.ui.KUserDataForView.Companion.buildAgeLocationStringForView
import nyc.c4q.okcupidchallenge.ui.KUserDataForView.Companion.buildCityStateStringForView
import nyc.c4q.okcupidchallenge.ui.KUserDataForView.Companion.buildMatchStringForView
import nyc.c4q.okcupidchallenge.ui.KUserDataForView.Companion.convertMatchToPercentage

@Parcelize
data class KUser(
        @SerializedName("username")
        val userName: String,
        @SerializedName("state_code")
        val stateCode: String,
        @SerializedName("city_name")
        val cityName: String,
        val age: Int,
        @SerializedName("match")
        val matchPercentage: Int,
        @SerializedName("photo")
        val photos: KPhotos,
        var isLiked: Boolean
) : Parcelable {

    fun getLocationAsCityState(): String {
        return buildCityStateStringForView(cityName, stateCode)
    }

    fun getMatchForView(): String {
        return buildMatchStringForView(convertMatchToPercentage(matchPercentage))
    }

    fun getAgeLocationForView(): String {
        return buildAgeLocationStringForView(age, getLocationAsCityState())
    }

}


