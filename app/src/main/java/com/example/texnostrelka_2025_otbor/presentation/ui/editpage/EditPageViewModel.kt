package com.example.texnostrelka_2025_otbor.presentation.ui.editpage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.texnostrelka_2025_otbor.data.model.ImageModel
import com.example.texnostrelka_2025_otbor.data.model.PageWithImagesIdsModel
import com.example.texnostrelka_2025_otbor.domain.repository.ComicsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPageViewModel @Inject constructor(
    private val repository: ComicsRepository
) : ViewModel() {
    private val _pageWithImages = MutableLiveData<PageWithImagesIdsModel>()
    val pageWithImages: LiveData<PageWithImagesIdsModel> get() = _pageWithImages

    private val _images = MutableLiveData<List<ImageModel>>()
    val images: LiveData<List<ImageModel>> get() = _images

    fun fetchPageWithImages(pageId: String) {
        viewModelScope.launch {
            val page = repository.getMyPage(pageId).find { it.pageId == pageId }
            if (page != null) {
                val imageIds = repository.getAllImagesOnPage(pageId).map { it.id!! }
                _pageWithImages.value = PageWithImagesIdsModel(page, imageIds)
            }
        }
    }

    fun fetchImages(pageId: String) {
        viewModelScope.launch {
            val images = repository.getAllImagesOnPage(pageId)
            _images.value = images
        }
    }
}