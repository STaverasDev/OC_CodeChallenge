package nyc.c4q.okcupidchallenge.ui


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_profile.*

import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.model.KUser

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

    private var userBundle: Bundle? = null
    lateinit var rootView: View
    lateinit var user: KUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userBundle = it.getBundle(USER)

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        user = userBundle?.get("selected_user") as KUser
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profile_user_name.text = user.userName
        Picasso.get().load(user.photos.photoThumbnails.mediumThumbnail).into(profile_user_img)
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
