package com.juliensacre.findcar.ui.detailcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.juliensacre.findcar.data.local.entity.Car
import kotlinx.android.synthetic.main.fragment_detail_car.*



class DetailCarFragment : Fragment() {
    private lateinit var car: Car

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(com.juliensacre.findcar.R.layout.fragment_detail_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val safeArgs = arguments?.let { DetailCarFragmentArgs.fromBundle(it) }
        car = safeArgs!!.car

        bindUI(car)
    }

    private fun bindUI(car: Car) {
        Glide.with(this@DetailCarFragment)
            .load(car.picture)
            .into(imageView_car_detail)
        textView_make_detail.text = car.make
        textView_model_year_detail.text = "${car.model} (${car.year})" //TODO add in string values
        initListEquipement(car.equipments)
    }

    private fun initListEquipement(equipements : List<String>){
        //TODO create item layout
        //TODO check if equipement is empty and change text if yes
        val adapter = ArrayAdapter<String>(
            this.context!!, android.R.layout.simple_list_item_1, android.R.id.text1, car.equipments
        )
        list.adapter = adapter
    }


}
