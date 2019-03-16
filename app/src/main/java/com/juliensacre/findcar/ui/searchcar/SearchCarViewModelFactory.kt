package com.juliensacre.findcar.ui.searchcar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.juliensacre.findcar.data.Repository

// Wait to see if Koin automaticaly create a factory for the viewmodel
class SearchCarViewModelFactory(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory( ){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return SearchCarViewModel(repository ) as T
    }
}