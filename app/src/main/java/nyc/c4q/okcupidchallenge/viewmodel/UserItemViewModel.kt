package nyc.c4q.okcupidchallenge.viewmodel

import android.databinding.BaseObservable
import android.databinding.Bindable
import android.databinding.Observable
import nyc.c4q.okcupidchallenge.R
import nyc.c4q.okcupidchallenge.model.KUser


class UserItemViewModel(var user: KUser) : BaseObservable(), Observable {


    @Bindable
    fun setNewUser(user: KUser) {
        this.user = user
        notifyChange()
    }

    @Bindable
    fun getUserName(): String {
        return user.userName
    }

    @Bindable
    fun getAgeLocationForView(): String {
        return user.getAgeLocationForView()
    }

    @Bindable
    fun getMatchForView(): String {
        return user.getMatchForView()
    }

    @Bindable
    fun getUserImg(): String {
        return user.photos.photoThumbnails.mediumThumbnail
    }

    @Bindable
    private fun getIsLiked(): Boolean {
        return user.isLiked
    }

    @Bindable
    fun getLikedIndicatorColor(): Int {
        if (getIsLiked()) {
            return R.color.likedColor
        }
        return R.color.unlikedColor
    }


}