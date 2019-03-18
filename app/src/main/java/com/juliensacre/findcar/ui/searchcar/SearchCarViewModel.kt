package com.juliensacre.findcar.ui.searchcar

import androidx.lifecycle.ViewModel
import com.juliensacre.findcar.data.Repository
import com.juliensacre.findcar.internal.lazyDeferred

class SearchCarViewModel(
    private val repository: Repository
) : ViewModel() {

    val cars by lazyDeferred { repository.getCars() }

}
