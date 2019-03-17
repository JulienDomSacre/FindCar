package com.juliensacre.findcar.ui.searchcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliensacre.findcar.R
import com.juliensacre.findcar.data.local.entity.Car
import com.juliensacre.findcar.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_search_car.*
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
        return inflater.inflate(R.layout.fragment_search_car, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bindUi()
    }

    //Just use launch because my fragment inheriting of scopedfragment
    // and it is itself an instance of coroutine
    private fun bindUi() = launch{
        val car = viewModel.cars.await()
        car.observe(this@SearchCarFragment, Observer {
            //for the first launch where the bd is empty and return null
            if(it == null) return@Observer

            group_loading.visibility = View.GONE
            initRecyclerView(it.toCarItems())
        })
    }

    /**
     * the adapter want CarItem, this fonction extension change the list of Car to a list of CarItem
     */
    private fun List<Car>.toCarItems() : List<CarItem>{
        return this.map {
            CarItem(it)
        }
    }

    private fun initRecyclerView(items : List<CarItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchCarFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? CarItem)?.let {
                //showCarDetail(it.carEntry, view)
                Toast.makeText(this@SearchCarFragment.context,"${item.carEntry.model} are clicked",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
