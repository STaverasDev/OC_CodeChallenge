package nyc.c4q.okcupidchallenge.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import nyc.c4q.okcupidchallenge.model.KUser

class UserItemViewModel(var user: KUser) : BaseObservable() {


    @Bindable
    fun setNewUser(user: KUser) {
        this.user = user
    }

    @Bindable
    fun getUserName():String{
        return user.userName
    }

    @Bindable
    fun getAgeLocationForView():String{
        return user.getAgeLocationForView()
    }

    @Bindable
    fun getMatchforView():String{
        return user.getAgeLocationForView()
    }

    @Bindable
    fun getUserImg():String{
        return user.photos.photoThumbnails.mediumThumbnail
    }

}