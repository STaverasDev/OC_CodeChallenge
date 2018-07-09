package nyc.c4q.okcupidchallenge.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_search.*
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.viewmodel.KSearchViewModel
import nyc.c4q.okcupidchallenge.recview.KUserAdapter
import nyc.c4q.okcupidchallenge.viewmodel.SearchViewModelProvider

class KSearchActivity : AppCompatActivity(), KSearchContract.View {

    private var searchViewModelProvider: SearchViewModelProvider = SearchViewModelProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        setAppBarTitle()
        val viewModel = ViewModelProviders.of(this, searchViewModelProvider).get(KSearchViewModel::class.java)
        viewModel.userLiveData.observe(this, android.arch.lifecycle.Observer { list ->
            if (list != null) {
                showUserRecyclerView(list)
            }
        })
    }

    private fun startProfileFragment(user: Bundle) {
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

    private fun observeLiveData(adapter: KUserAdapter) {
        adapter.userSelectedLiveData.observe(this, android.arch.lifecycle.Observer { user -> startProfileFragment(createUserBundle(user)) })
    }

    private fun createUserBundle(user: KUser?): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(SELECTED_USER_BUNDLE_KEY, user)
        return bundle
    }

    override fun showErrorMessage() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.network_error_message)
        builder.setPositiveButton(R.string.network_error_retry, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
        })
        builder.setNegativeButton(R.string.network_error_retry, DialogInterface.OnClickListener { dialogInterface: DialogInterface, i: Int ->
            finish()
        })
        val dialog = builder.create()
        dialog.show()
    }

    companion object {
        private const val HORIZONTAL_SPAN = 3
        private const val PROFILE_FRAG_NAME = "profile_frag"
        private const val SELECTED_USER_BUNDLE_KEY = "selected_user"
    }


}