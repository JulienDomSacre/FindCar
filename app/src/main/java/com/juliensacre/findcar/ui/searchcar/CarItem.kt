package com.juliensacre.findcar.ui.searchcar

import com.bumptech.glide.Glide
import com.juliensacre.findcar.R
import com.juliensacre.findcar.data.local.entity.Car
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.item_car.*

class CarItem(
    val carEntry : Car
) : Item() {
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.apply {
            textView_make.text = carEntry.make
            textView_model.text = carEntry.model
            Glide.with(this.containerView)
                .load(carEntry.picture)
                .into(imageView_picture)
        }
    }

    override fun getLayout(): Int = R.layout.item_car
}