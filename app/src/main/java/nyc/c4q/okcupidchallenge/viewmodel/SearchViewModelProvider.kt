package nyc.c4q.okcupidchallenge.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import nyc.c4q.okcupidchallenge.api.KOkCupidService

class SearchViewModelProvider: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return KSearchViewModel(KOkCupidService()) as T
    }
}