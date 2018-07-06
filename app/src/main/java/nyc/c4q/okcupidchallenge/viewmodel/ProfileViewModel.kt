package nyc.c4q.okcupidchallenge.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import nyc.c4q.okcupidchallenge.model.KUser

class ProfileViewModel(val user: KUser) : ViewModel() {

    val username: ObservableField<String> = ObservableField()
   // val userImg: ObservableField<String> = ObservableField()

    init {
        initialize()
    }

    fun getTestName():String{
        return "testname"
    }

    fun initialize(){
        username.set(user.userName)
        //userImg.set(user.photos.photoThumbnails.mediumThumbnail)
    }

}