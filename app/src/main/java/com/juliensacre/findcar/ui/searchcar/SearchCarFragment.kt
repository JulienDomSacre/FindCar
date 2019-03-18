package com.juliensacre.findcar.ui.searchcar

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.juliensacre.findcar.R
import com.juliensacre.findcar.data.local.entity.Car
import com.juliensacre.findcar.ui.base.ScopedFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.fragment_search_car.*
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class SearchCarFragment : ScopedFragment(), SearchView.OnQueryTextListener {
    //inject
    private val viewModel : SearchCarViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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
            //for the first launch where the db is empty and return null
            if(it == null) return@Observer

            group_loading.visibility = View.GONE
            initRecyclerView(it.toCarItems())
        })
    }

    /**
     * the adapter want CarItem, this function extension change the list of Car to a list of CarItem
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
                showCarDetail(it.carEntry, view)
            }
        }
    }

    private fun showCarDetail(car: Car, view : View){
        val actionDetail = SearchCarFragmentDirections.actionDetail(car)
        Navigation.findNavController(view).navigate(actionDetail)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search,menu)
        val searchManager  = activity!!.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.menu_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(activity!!.componentName))
        searchView.setIconifiedByDefault(true)
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater);
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        Timber.d("text submit: $query")
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        Timber.d("text change: $newText")
        return true
    }
}
