package nyc.c4q.okcupidchallenge.ui.profilebinding

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

@BindingAdapter("app:picassoImg")
fun setPicassoImg(view: ImageView, url: String) {
    Picasso.get().load(url).into(view)
}

@BindingAdapter("testTextView")
fun setTestTextView(view: TextView, url: Int) {
}
