package nyc.c4q.okcupidchallenge.presenter;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.okcupidchallenge.api.OkCupidService;
import nyc.c4q.okcupidchallenge.model.APIResponse;
import nyc.c4q.okcupidchallenge.model.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View view;
    private OkCupidService service;
    private List<User> userList = new ArrayList<>();


    public SearchPresenter(SearchContract.View view, OkCupidService service) {
        this.view = view;
        this.service = service;
    }

    public SearchPresenter(SearchContract.View view,List<User> userList){
        this.userList = userList;
        this.view = view;
    }

    @Override
    public void startWithNetworkCall() {
        getUsers();
        view.setUpRecView();
    }

    @Override
    public void getUsers() {
        Call<APIResponse> call = service.getOkCupidAPI().getAPIResponse();

        call.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                userList = response.body().getUserList();
                view.showUserRecyclerView(userList);
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                view.showErrorMessage();
            }
        });

    }

    @Override
    public void showSavedList() {
        view.setUpRecView();
        view.showUserRecyclerView(userList);
    }

    public List<User> getUserList() {
        return userList;
    }

}
