package com.mengyangsoft.myapplication.Extensions

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.mengyangsoft.myapplication.R


fun ImageView.load(url: String) {
    val requestOption = RequestOptions().placeholder(R.mipmap.icon_logo).error(R.mipmap.icon_logo).diskCacheStrategy(DiskCacheStrategy.ALL)
    get(url).apply(requestOption).into(this)
}

fun ImageView.loadRound(url: String) {
    val requestOption = RequestOptions().placeholder(R.mipmap.icon_logo).error(R.mipmap.icon_logo).transform(RoundedCorners(context.dp2px(6f))).diskCacheStrategy(DiskCacheStrategy.ALL)
    get(url).apply(requestOption).into(this)
}

fun ImageView.get(url: String): RequestBuilder<Drawable> = Glide.with(context).load(url)

fun ImageView.get(url: Drawable): RequestBuilder<Drawable> = Glide.with(context).load(url)