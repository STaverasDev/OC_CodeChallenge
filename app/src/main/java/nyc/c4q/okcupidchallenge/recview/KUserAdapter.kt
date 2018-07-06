package nyc.c4q.okcupidchallenge.recview

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.user.view.*
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.databinding.UserBinding
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.viewmodel.UserItemViewModel


class KUserAdapter(var userList: MutableList<KUser>) : RecyclerView.Adapter<KUserAdapter.KUserViewHolder>() {

    var userSelectedLiveData: MutableLiveData<KUser> = MutableLiveData()


    override fun onBindViewHolder(holder: KUserViewHolder, position: Int) {
        var user = userList[position]
        holder.bind(user)
        holder.setBackgroundColor(user.isLiked)
        holder.adapter = this

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KUserViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val userBinding: UserBinding = DataBindingUtil.inflate(inflater, R.layout.user, parent, false)
        return KUserViewHolder(view, userBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class KUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val userImage = itemView.user_img
        val username = itemView.user_name
        val userLocation = itemView.user_location
        val userMatch = itemView.user_match
        lateinit var mBinding: UserBinding
        lateinit var adapter: KUserAdapter
        lateinit var user: KUser

        constructor(itemview: View, binding: UserBinding) : this(itemview) {
            binding.root
            mBinding = binding
        }


        fun bind(user: KUser) {
            this.user = user
            itemView.setOnClickListener(this)
            val userItemViewModel: UserItemViewModel = UserItemViewModel(user)
            mBinding.recUserVM = userItemViewModel
            userItemViewModel.setNewUser(user)

            /*
            userLocation.text = user.getAgeLocationForView()
            userMatch.text = user.getMatchForView()
            username.text = user.userName
            Picasso.get().load(user.photos.photoThumbnails.mediumThumbnail).fit().into(userImage)*/
        }

        fun setLiveData(user: KUser) {
            adapter.userSelectedLiveData.postValue(user)

        }

        override fun onClick(p0: View?) {
            setLiveData(user)
            setBackgroundColor(!user.isLiked)
            user.isLiked = !user.isLiked
        }


        fun setBackgroundColor(isLiked: Boolean) {
            val colorResource: Int = if (isLiked) R.color.likedColor else R.color.unlikedColor
            val context = itemView.context
            itemView.setBackgroundColor(ContextCompat.getColor(context, colorResource))
        }


    }


}