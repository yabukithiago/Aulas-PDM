package com.examples.shoppinglist.ui.list

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
import com.examples.shoppinglist.ui.components.TopBar
import com.examples.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddListView(navController: NavController) {
    val viewModel: AddListViewModel = viewModel()
    val state by viewModel.state

    TopBar(title = "Add lists", navController = navController)

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
            label = { Text("Nome da Lista") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = state.description,
            onValueChange = viewModel::onDescriptionChange,
            label = { Text("Descrição da Lista") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        Button(
            onClick = {
                if (state.name.isNotEmpty() && state.description.isNotEmpty()) {
                    viewModel.addList(
                        state.name, state.description,
                        onSuccess = { navController.popBackStack() },
                        onFailure = { message -> state.errorMessage = message }
                    )
                } else {
                    state.errorMessage = "Preencha todos os campos."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Carregando..." else "Criar lista")
        }

        if (state.errorMessage?.isNotEmpty() == true) {
            state.errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun AddListViewPreview() {
    ShoppingListTheme() {
        AddListView(navController = rememberNavController())
    }
}