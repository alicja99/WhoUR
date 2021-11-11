package com.put.tsm.whour.data.repository

import com.put.tsm.whour.data.models.Category
import com.put.tsm.whour.data.models.CategoryItem
import com.put.tsm.whour.data.utils.Result

interface FirebaseRepository {
    suspend fun getAllCategories(forceUpdate: Boolean = false): Result<List<Category>>
    suspend fun getAllItems(forceUpdate: Boolean = false): Result<List<CategoryItem>>
    suspend fun getItemsFromCategory(
        categoryId: String,
        forceUpdate: Boolean = false
    ): Result<List<CategoryItem>>
}