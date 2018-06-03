package nyc.c4q.okcupidchallenge.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import static nyc.c4q.okcupidchallenge.model.UserDataForView.*;


public class User implements Parcelable {

    @SerializedName("username")
    private String userName;

    @SerializedName("state_code")
    private String stateCode;

    @SerializedName("city_name")
    private String cityCode;

    private int age;

    @SerializedName("match")
    private int matchPercentage;

    @SerializedName("photo")
    private Photos photos;

    private boolean isLiked;

    protected User(Parcel in) {
        userName = in.readString();
        stateCode = in.readString();
        cityCode = in.readString();
        age = in.readInt();
        matchPercentage = in.readInt();
        photos = in.readParcelable(Photos.class.getClassLoader());
        isLiked = in.readByte() != 0;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getUserName() {
        return userName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public int getAge() {
        return age;
    }

    public int getMatchPercentage() {
        return convertMatchToPercentage(matchPercentage);
    }

    public Photos getPhotos() {
        return photos;
    }

    public String getLocationAsCityState() {
        return buildCityStateStringForView(cityCode, stateCode);
    }

    public String getMatchForView() {
        return buildMatchStringForView(convertMatchToPercentage(matchPercentage));
    }

    public String getAgeLocationForView() {
        return buildAgeLocationStringForView(age, getLocationAsCityState());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(stateCode);
        parcel.writeString(cityCode);
        parcel.writeInt(age);
        parcel.writeInt(matchPercentage);
        parcel.writeParcelable(photos, i);
        parcel.writeByte((byte) (isLiked ? 1 : 0));
    }
}
