package com.examples.shoppinglist.ui.list

import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DeleteListView(navController: NavController, id: String) {
    var showDialog by remember { mutableStateOf(true) }
    val viewModel : DeleteListViewModel = viewModel()
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Confirmar Exclusão") },
            text = { Text("Tem certeza de que deseja excluir esta lista?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteList(
                        id = id,
                        onFailure = { exception ->
                            showDialog = false
                            Log.e("DeleteBeneficiary", "Erro ao excluir: ${exception.message}")
                        }
                    )
                }) {
                    Text("Sim")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Não")
                    navController.navigate("showLists")
                }
            }
        )
    }
}
