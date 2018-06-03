package nyc.c4q.okcupidchallenge.ui;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nyc.c4q.okcupidchallenge.R;
import nyc.c4q.okcupidchallenge.api.OkCupidService;
import nyc.c4q.okcupidchallenge.model.User;
import nyc.c4q.okcupidchallenge.presenter.SearchContract;
import nyc.c4q.okcupidchallenge.presenter.SearchPresenter;
import nyc.c4q.okcupidchallenge.recview.UserAdapter;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private static final int HORIZONTAL_SPAN = 4;
    private static final String USER_LIST_SAVED_TO_BUNDLE = "user_list";
    private SearchPresenter presenter;
    private RecyclerView userRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setAppBarTitle();

        if (savedInstanceState == null || !(savedInstanceState.containsKey(USER_LIST_SAVED_TO_BUNDLE))) {
            presenter = new SearchPresenter(this, new OkCupidService());
            presenter.startWithNetworkCall();
        } else {
            presenter = new SearchPresenter(this,savedInstanceState.<User>getParcelableArrayList(USER_LIST_SAVED_TO_BUNDLE));
            presenter.showSavedList();
        }
    }

    public void setAppBarTitle() {
        ActionBar actionBar = getSupportActionBar();
        String title = getResources().getString(R.string.search_appbar_title);
        actionBar.setTitle(title);
    }

    @Override
    public void showUserRecyclerView(List<User> userList) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            userRecView.setLayoutManager(new GridLayoutManager(this, HORIZONTAL_SPAN));
        }
        UserAdapter adapter = new UserAdapter(userList);
        userRecView.setAdapter(adapter);
    }

    @Override
    public void showErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.network_error_message);
        builder.setPositiveButton(R.string.network_error_retry, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                presenter.startWithNetworkCall();
            }
        });
        builder.setNegativeButton(R.string.network_error_closeapp, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void setUpRecView() {
        userRecView = findViewById(R.id.user_rec_view);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(USER_LIST_SAVED_TO_BUNDLE, (ArrayList<? extends Parcelable>) presenter.getUserList());
        super.onSaveInstanceState(outState);
    }

}
