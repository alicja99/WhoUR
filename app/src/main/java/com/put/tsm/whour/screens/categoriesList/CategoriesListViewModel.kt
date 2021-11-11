package com.put.tsm.whour.screens.categoriesList

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.put.tsm.whour.data.models.Category
import com.put.tsm.whour.data.repository.usecases.GetAllCategoriesUseCase
import com.put.tsm.whour.data.repository.usecases.GetAllItemsUseCase
import com.put.tsm.whour.data.repository.usecases.GetItemsFromCategoryUseCase
import com.put.tsm.whour.data.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesListViewModel @Inject constructor(private val getAllCategories: GetAllCategoriesUseCase) :
    ViewModel() {

    var categoriesList = mutableStateOf<List<Category>>(listOf())
    var isLoading = mutableStateOf(false)
    var loadError = mutableStateOf<Exception?>(null)

    init {
        viewModelScope.launch {
            isLoading.value = true
            when (val result = getAllCategories()) {
                is Result.Success -> categoriesList.value = result.data
                is Result.Error -> loadError.value = result.exception
            }
            isLoading.value = false
        }
    }
}