package nyc.c4q.okcupidchallenge.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KPhotoThumbnails(
        @SerializedName("medium")
        val mediumThumbnail: String) : Parcelable