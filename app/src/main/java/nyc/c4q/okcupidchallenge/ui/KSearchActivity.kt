package nyc.c4q.okcupidchallenge.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_search.*
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.api.KOkCupidService
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.viewmodel.KSearchContract
import nyc.c4q.okcupidchallenge.viewmodel.KSearchViewModel
import nyc.c4q.okcupidchallenge.recview.KUserAdapter
import nyc.c4q.okcupidchallenge.viewmodel.SearchViewModelProvider
import java.util.*

class KSearchActivity : AppCompatActivity(), KSearchContract.View {

    val HORIZONTAL_SPAN = 3
    val USER_LIST_SAVED_TO_BUNDLE = "user_list"
    val PROFILE_FRAG_NAME = "profile_frag"
    val SELECTED_USER_BUNDLE_KEY = "selected_user"

    //lateinit var viewModel: KSearchViewModel
    var searchViewModelProvider: SearchViewModelProvider = SearchViewModelProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setAppBarTitle()

        val viewModel = ViewModelProviders.of(this, searchViewModelProvider).get(KSearchViewModel::class.java)

        viewModel.userLiveData.observe(this, android.arch.lifecycle.Observer { list ->
            Log.d("${this.javaClass.simpleName}", "observing list")

            if (list != null) {
                Log.d("${this.javaClass.simpleName}", "received list")
                showUserRecyclerView(list)
            }

        })

    }

    fun startProfileFragment(user: Bundle) {
        Log.d("${this.javaClass.simpleName}", "starting frag")
        val transaction = supportFragmentManager.beginTransaction()
        val profileFragment = ProfileFragment.newInstance(user)
        transaction.add(R.id.frag_container, profileFragment).addToBackStack(PROFILE_FRAG_NAME)
        transaction.commit()
    }

    override fun setAppBarTitle() {
        supportActionBar?.title = resources.getString(R.string.search_appbar_title)
    }

    override fun showUserRecyclerView(userList: MutableList<KUser>) {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            user_rec_view.layoutManager = GridLayoutManager(this, HORIZONTAL_SPAN)
        }
        val adapter = KUserAdapter(userList)
        user_rec_view.adapter = adapter
        observeLiveData(adapter)
    }

    fun observeLiveData(adapter: KUserAdapter) {
        adapter.userSelectedLiveData.observe(this, android.arch.lifecycle.Observer { user -> startProfileFragment(createUserBundle(user)) })
    }

    fun createUserBundle(user: KUser?): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_USER_BUNDLE_KEY, user)
        return bundle
    }

    override fun showErrorMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.network_error_message)
        builder.setPositiveButton(R.string.network_error_retry, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            //viewModel.initialize()
        })
        builder.setNegativeButton(R.string.network_error_retry, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        val dialog = builder.create()
        dialog.show()
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        //outState?.putParcelableArrayList(USER_LIST_SAVED_TO_BUNDLE, viewModel.userLiveData as ArrayList<out Parcelable>)
        super.onSaveInstanceState(outState, outPersistentState)
    }


}