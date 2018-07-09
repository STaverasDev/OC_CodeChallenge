package nyc.c4q.okcupidchallenge.recview

import android.arch.lifecycle.MutableLiveData
import android.databinding.DataBindingUtil
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.databinding.UserBinding
import nyc.c4q.okcupidchallenge.model.KUser
import nyc.c4q.okcupidchallenge.viewmodel.UserItemViewModel


class KUserAdapter(var userList: MutableList<KUser>) : RecyclerView.Adapter<KUserAdapter.KUserViewHolder>() {

    var userSelectedLiveData: MutableLiveData<KUser> = MutableLiveData()

    override fun onBindViewHolder(holder: KUserViewHolder, position: Int) {
        var user = userList[position]
        holder.bind(user)
        holder.userSelectedLiveData = userSelectedLiveData
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KUserViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val userBinding: UserBinding = DataBindingUtil.inflate(inflater, R.layout.user, parent, false)
        return KUserViewHolder(userBinding)
    }

    override fun getItemCount(): Int {
        return userList.size
    }


    class KUserViewHolder(val binding: UserBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var userSelectedLiveData: MutableLiveData<KUser> = MutableLiveData()
        private lateinit var userItemViewModel: UserItemViewModel

        fun bind(user: KUser) {
            userItemViewModel = UserItemViewModel(user)
            itemView.setOnClickListener(this)
            binding.recUserVM = userItemViewModel
            userItemViewModel.setNewUser(user)

        }

        override fun onClick(p0: View?) {
            userSelectedLiveData.postValue(userItemViewModel.user)
            userItemViewModel.user.isLiked = !userItemViewModel.user.isLiked
            userItemViewModel.setIsLiked(userItemViewModel.user.isLiked)
        }

    }


}