package nyc.c4q.okcupidchallenge.presenter;

import java.util.List;

import nyc.c4q.okcupidchallenge.model.User;

public interface SearchContract {

    interface View {
        void setAppBarTitle();

        void showUserRecyclerView(List<User> userList);

        void showErrorMessage();

        void setUpRecView();

    }

    interface Presenter {
        void startWithNetworkCall();

        void getUsers();

        void showSavedList();
    }

}
