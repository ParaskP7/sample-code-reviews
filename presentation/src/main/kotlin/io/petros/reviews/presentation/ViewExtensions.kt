package io.petros.reviews.presentation

import android.support.annotation.LayoutRes
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(@LayoutRes resource: Int): View {
    return View.inflate(context, resource, this)
}
