package com.examples.shoppinglist.ui.list

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
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
import com.examples.shoppinglist.data.repository.ListItemRepository
import com.examples.shoppinglist.ui.components.TopBar
import com.examples.shoppinglist.ui.theme.ShoppingListTheme
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun EditListView(navController: NavController, id: String) {
    val viewModel: AddListViewModel = viewModel()
    val state by viewModel.state
    val db = FirebaseFirestore.getInstance()

    LaunchedEffect(id) {
        db.collection("listTypes").document(id).get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    state.name = document.getString("name") ?: ""
                    state.description = document.getString("description") ?: ""
                }
            }
            .addOnFailureListener { e ->
                Log.e("EditList", "Erro ao carregar listas: ${e.message}")
            }
    }

    TopBar(title = "Editar Listas", navController = navController)

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
            painter = painterResource(id = R.drawable.baseline_edit_24),
            contentDescription = "Edit Icon"
        )

        TextField(
            value = state.name,
            onValueChange = viewModel::onNameChange,
            label = { Text("Nome") },
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
            label = { Text("Telefone") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(12.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (state.name.isNotEmpty() && state.description.isNotEmpty()) {
                    ListItemRepository.updateList(id, state.name, state.description,
                        onSuccess = { navController.navigate("showLists") },
                        onFailure = { message -> state.errorMessage = message }
                    )
                } else {
                    state.errorMessage = "Preencha todos os campos."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "Carregando..." else "Editar Lista")
        }

        if (state.errorMessage?.isNotEmpty() == true) {
            state.errorMessage?.let { Text(text = it, color = MaterialTheme.colorScheme.error) }
        }
    }
}

@Preview (showBackground = true)
@Composable
fun EditListViewPreview() {
    ShoppingListTheme {
        EditListView(navController = rememberNavController(), id="123")
    }
}