package nyc.c4q.okcupidchallenge.model;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class Photos implements Parcelable {
    @SerializedName("thumb_paths")
    private PhotoThumbnails photoThumbnails;

    protected Photos(android.os.Parcel in) {
        photoThumbnails = in.readParcelable(PhotoThumbnails.class.getClassLoader());
    }

    public static final Creator<Photos> CREATOR = new Creator<Photos>() {
        @Override
        public Photos createFromParcel(android.os.Parcel in) {
            return new Photos(in);
        }

        @Override
        public Photos[] newArray(int size) {
            return new Photos[size];
        }
    };

    public PhotoThumbnails getPhotoThumbnails() {
        return photoThumbnails;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel parcel, int i) {
        parcel.writeParcelable(photoThumbnails, i);
    }
}
