package com.juliensacre.findcar.ui.searchcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.juliensacre.findcar.R
import com.juliensacre.findcar.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.search_car_fragment.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class SearchCarFragment : ScopedFragment() {

    //inject
    //private val viewModelFactory: SearchCarViewModelFactory by inject()
    private val viewModel : SearchCarViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_car_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
/*
        val apiService = CarAPIService(ConnectivityInterceptorImpl(this.context!!))
        val carDataSource = CarDataSourceImpl(apiService)

        carDataSource.downloadedCars.observe(this, Observer { Log.d("TOTO", "${it.toString()}") })

        GlobalScope.launch(Dispatchers.Main) {
            carDataSource.fetchCarList()
        }
*/
    }

    //Just use launch because my fragment inheriting of scopedfragment
    // and it is itself an instance of coroutine
    private fun bindUi() = launch{
        val car = viewModel.cars.await()
        car.observe(this@SearchCarFragment, Observer {
            //for the first launch where the bd is empty and return null
            if(it == null) return@Observer
            message.text = it.toString() })
    }
}
