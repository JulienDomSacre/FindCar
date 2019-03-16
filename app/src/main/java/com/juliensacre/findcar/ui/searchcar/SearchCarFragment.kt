package com.juliensacre.findcar.ui.searchcar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.juliensacre.findcar.R
import com.juliensacre.findcar.data.remote.CarAPIService
import com.juliensacre.findcar.data.remote.CarDataSourceImpl
import com.juliensacre.findcar.data.remote.ConnectivityInterceptorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchCarFragment : Fragment() {

    companion object {
        fun newInstance() = SearchCarFragment()
    }

    private lateinit var viewModel: SearchCarViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.search_car_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SearchCarViewModel::class.java)
        // TODO: Use the ViewModel

        val apiService = CarAPIService(ConnectivityInterceptorImpl(this.context!!))
        val carDataSource = CarDataSourceImpl(apiService)

        carDataSource.downloadedCars.observe(this, Observer { Log.d("TOTO", "${it.toString()}") })

        GlobalScope.launch(Dispatchers.Main) {
            carDataSource.fetchCarList()
        }

    }

}
