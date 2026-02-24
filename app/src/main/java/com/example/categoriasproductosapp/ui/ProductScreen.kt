package com.example.categoriasproductosapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.categoriasproductosapp.data.Product

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    viewModel: ProductViewModel,
    categoryId: Int,
    onBack: () -> Unit
) {
    val products by viewModel.getProductsByCategory(categoryId).collectAsState(initial = emptyList())
    var showDialog by remember { mutableStateOf(false) }
    var editingProduct by remember { mutableStateOf<Product?>(null) }
    var productName by remember { mutableStateOf("") }
    var productPrice by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Productos") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                editingProduct = null
                productName = ""
                productPrice = ""
                showDialog = true
            }) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(products) { product ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                            Text(text = "S/ ${product.price}", style = MaterialTheme.typography.bodySmall)
                        }
                        IconButton(onClick = {
                            editingProduct = product
                            productName = product.name
                            productPrice = product.price.toString()
                            showDialog = true
                        }) {
                            Icon(Icons.Default.Edit, contentDescription = "Editar")
                        }
                        IconButton(onClick = { viewModel.delete(product) }) {
                            Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                        }
                    }
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(if (editingProduct == null) "Nuevo Producto" else "Editar Producto") },
                text = {
                    Column {
                        TextField(
                            value = productName,
                            onValueChange = { productName = it },
                            label = { Text("Nombre") }
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        TextField(
                            value = productPrice,
                            onValueChange = { productPrice = it },
                            label = { Text("Precio") }
                        )
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        val price = productPrice.toDoubleOrNull() ?: 0.0
                        if (editingProduct == null) {
                            viewModel.insert(Product(name = productName, price = price, categoryId = categoryId))
                        } else {
                            viewModel.update(editingProduct!!.copy(name = productName, price = price))
                        }
                        showDialog = false
                    }) {
                        Text("Guardar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) { Text("Cancelar") }
                }
            )
        }
    }
}
