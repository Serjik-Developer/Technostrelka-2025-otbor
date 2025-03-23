package com.example.texnostrelka_2025_otbor.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.texnostrelka_2025_otbor.repositories.ComicsRepository
import com.example.texnostrelka_2025_otbor.viewmodels.ViewViewModel

class ViewViewModelFactory(private val comicsId: String, private val repository: ComicsRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(ViewViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewViewModel(comicsId, repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}