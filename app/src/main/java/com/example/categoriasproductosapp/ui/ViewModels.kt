package com.example.categoriasproductosapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.categoriasproductosapp.data.AppDatabase
import com.example.categoriasproductosapp.data.Category
import com.example.categoriasproductosapp.data.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).categoryDao()
    val allCategories: Flow<List<Category>> = dao.getAllCategories()

    fun insert(category: Category) = viewModelScope.launch { dao.insertCategory(category) }
    fun update(category: Category) = viewModelScope.launch { dao.updateCategory(category) }
    fun delete(category: Category) = viewModelScope.launch { dao.deleteCategory(category) }
}

class ProductViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).productDao()

    fun getProductsByCategory(categoryId: Int): Flow<List<Product>> = dao.getProductsByCategory(categoryId)

    fun insert(product: Product) = viewModelScope.launch { dao.insertProduct(product) }
    fun update(product: Product) = viewModelScope.launch { dao.updateProduct(product) }
    fun delete(product: Product) = viewModelScope.launch { dao.deleteProduct(product) }
}
