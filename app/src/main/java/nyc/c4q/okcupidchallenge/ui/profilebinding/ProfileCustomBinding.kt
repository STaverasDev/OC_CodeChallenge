package nyc.c4q.okcupidchallenge.ui.profilebinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("app:picassoImg")
fun setPicassoImg(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}

