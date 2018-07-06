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
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val userBinding: UserBinding = DataBindingUtil.inflate(inflater, R.layout.user, parent, false)
        val view = userBinding.root
        return KUserViewHolder(view, userBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class KUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        lateinit var mBinding: UserBinding
        lateinit var adapter: KUserAdapter
        lateinit var user: KUser

        constructor(itemview: View, binding: UserBinding) : this(itemview) {

            mBinding = binding
        }

        fun bind(user: KUser) {
            this.user = user
            itemView.setOnClickListener(this)
            val userItemViewModel = UserItemViewModel(user)
            mBinding.recUserVM = userItemViewModel
            userItemViewModel.setNewUser(user)

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