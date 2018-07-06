package nyc.c4q.okcupidchallenge.recview

import android.arch.lifecycle.MutableLiveData
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.user.view.*
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.ui.ProfileFragment


class KUserAdapter(var userList: MutableList<KUser>) : RecyclerView.Adapter<KUserAdapter.KUserViewHolder>() {

    var userSelectedLiveData: MutableLiveData<KUser> = MutableLiveData()


    override fun onBindViewHolder(holder: KUserViewHolder, position: Int) {
        var user = userList[position]
        holder.bind(user)
        holder.setBackgroundColor(user.isLiked)
        holder.adapter=this

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KUserViewHolder {
        val view: View= LayoutInflater.from(parent.context).inflate(R.layout.user, parent, false)
        return KUserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class KUserViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview),View.OnClickListener {

        val userImage = itemview.user_img
        val username = itemview.user_name
        val userLocation = itemview.user_location
        val userMatch = itemview.user_match
        lateinit var adapter:KUserAdapter
        lateinit var user: KUser


        fun bind(user: KUser) {
            this.user = user
            itemView.setOnClickListener(this)
            userLocation.text = user.getAgeLocationForView()
            userMatch.text = user.getMatchForView()
            username.text = user.userName
            Picasso.get().load(user.photos.photoThumbnails.mediumThumbnail).fit().into(userImage)
        }

        fun setLiveData(user:KUser){
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