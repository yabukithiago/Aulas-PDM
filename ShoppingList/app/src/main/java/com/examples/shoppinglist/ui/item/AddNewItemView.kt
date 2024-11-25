package com.examples.shoppinglist.ui.item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examples.shoppinglist.R
import com.examples.shoppinglist.data.repository.ItemRepository
import com.examples.shoppinglist.ui.components.TopBar
import com.examples.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddNewItemView(navController: NavController, listId: String) {
    val viewModel: AddNewItemViewModel = viewModel()
    val state by viewModel.state

    TopBar(title = "Add Items", navController = navController)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(150.dp),
            painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
            contentDescription = "Cart Icon"
        )

        TextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Product Name") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.quantity,
            onValueChange = viewModel::onQuantityChange,
            label = { Text("Product Quantity") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (state.name.isNotEmpty() && state.quantity.isNotEmpty()) {
                    ItemRepository.addItem(listId, state.name, state.quantity,
                        onSuccess = { navController.navigate("showLists") },
                        onFailure = { message -> state.errorMessage = message }
                    )
                } else {
                    state.errorMessage = "Fill in all the fields."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Loading..." else "New list")
        }

        if (state.errorMessage?.isNotEmpty() == true) {
            state.errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddListViewPreview() {
    ShoppingListTheme {
        com.examples.shoppinglist.ui.list.AddListView(navController = rememberNavController())
    }
}