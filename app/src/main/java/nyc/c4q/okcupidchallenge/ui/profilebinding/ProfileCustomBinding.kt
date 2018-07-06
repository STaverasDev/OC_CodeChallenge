package nyc.c4q.okcupidchallenge.ui.profilebinding

import android.databinding.BindingAdapter
import android.databinding.ObservableField
import android.widget.ImageView
import com.squareup.picasso.Picasso

@BindingAdapter("android:picassoImg")
fun setPicassoImg(view:ImageView, url:String){
    Picasso.get().load(url).into(view)}
