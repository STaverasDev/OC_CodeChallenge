package nyc.c4q.okcupidchallenge.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class KPhotos(
        @SerializedName("thumb_paths")
        val photoThumbnails: KPhotoThumbnails

) : Parcelable