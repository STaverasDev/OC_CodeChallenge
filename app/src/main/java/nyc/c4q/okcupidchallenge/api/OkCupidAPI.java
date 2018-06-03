package nyc.c4q.okcupidchallenge.api;

import nyc.c4q.okcupidchallenge.model.APIResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OkCupidAPI {

    @GET("/matchSample.json")
    Call<APIResponse> getAPIResponse();

}
