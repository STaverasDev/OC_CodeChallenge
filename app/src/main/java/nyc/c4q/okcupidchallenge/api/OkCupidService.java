package nyc.c4q.okcupidchallenge.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkCupidService {

    private static final String BASE_URL = "https://www.okcupid.com";

    private final Retrofit retrofit;

    public OkCupidService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public OkCupidAPI getOkCupidAPI() {
        return retrofit.create(OkCupidAPI.class);
    }

}
