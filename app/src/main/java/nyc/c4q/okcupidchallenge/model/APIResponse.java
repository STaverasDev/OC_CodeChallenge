package nyc.c4q.okcupidchallenge.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class APIResponse {

    @SerializedName("data")
    private List<User> userList;

    public List<User> getUserList() {
        return userList;
    }

}
