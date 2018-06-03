package nyc.c4q.okcupidchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class PhotoThumbnails implements Parcelable {

    @SerializedName("medium")
    private String mediumThumbnail;

    protected PhotoThumbnails(Parcel in) {
        mediumThumbnail = in.readString();
    }

    public static final Creator<PhotoThumbnails> CREATOR = new Creator<PhotoThumbnails>() {
        @Override
        public PhotoThumbnails createFromParcel(Parcel in) {
            return new PhotoThumbnails(in);
        }

        @Override
        public PhotoThumbnails[] newArray(int size) {
            return new PhotoThumbnails[size];
        }
    };

    public String getMediumThumbnail() {
        return mediumThumbnail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mediumThumbnail);
    }
}
