package nyc.c4q.okcupidchallenge.viewmodel

import android.databinding.BaseObservable
import nyc.c4q.okcupidchallenge.model.KUser

class UserItemViewModel(var user: KUser) : BaseObservable() {

    fun setNewUser(user: KUser) {
        this.user = user
    }


}