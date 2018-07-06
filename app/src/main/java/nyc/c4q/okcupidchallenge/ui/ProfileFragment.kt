package nyc.c4q.okcupidchallenge.ui


import android.databinding.BindingAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.databinding.FragmentProfileBinding
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.viewmodel.ProfileViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val USER = "user"


/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileFragment : Fragment() {

    val SELECTED_USER_BUNDLE_KEY = "selected_user"
    private var userBundle: Bundle? = null
    lateinit var rootView: View
    lateinit var user: KUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userBundle = it.getBundle(USER)
            val fragmentProfileBinding: FragmentProfileBinding = FragmentProfileBinding.inflate(layoutInflater)
            user = userBundle?.get(SELECTED_USER_BUNDLE_KEY) as KUser
            fragmentProfileBinding.userVM = ProfileViewModel(user)
            rootView = fragmentProfileBinding.root

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(user: Bundle) =
                ProfileFragment().apply {
                    arguments = Bundle().apply {
                        putBundle(USER, user)

                    }
                }
    }
}


