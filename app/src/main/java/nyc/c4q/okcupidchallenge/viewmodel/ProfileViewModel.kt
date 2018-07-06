package nyc.c4q.okcupidchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.Bindable
import android.databinding.ObservableField
import nyc.c4q.okcupidchallenge.model.KUser

class ProfileViewModel(var user: KUser) : BaseObservableViewModel() {

    val userName: ObservableField<String> = ObservableField()
    val userImg: ObservableField<String> = ObservableField()

    init {
        initialize()
    }

    fun initialize() {
        userName.set(user.userName)
        userImg.set(user.photos.photoThumbnails.mediumThumbnail)
    }

}