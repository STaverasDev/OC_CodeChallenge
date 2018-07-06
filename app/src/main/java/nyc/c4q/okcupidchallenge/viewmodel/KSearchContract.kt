package nyc.c4q.okcupidchallenge.viewmodel

import nyc.c4q.okcupidchallenge.model.KUser

interface KSearchContract {

    interface View {
        fun setAppBarTitle()
        fun showUserRecyclerView(userList: MutableList<KUser>)
        fun showErrorMessage()
    }


}