package com.examples.shoppinglist.ui.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examples.shoppinglist.data.repository.ItemRepository.countItems

@Composable
fun ListCard(navController: NavController, id: String, name: String, description: String, onClick: () -> Unit) {
    var itemCount by remember { mutableIntStateOf(0) }
    var menuExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        countItems(
            listTypeId = id,
            onSuccess = { count ->
                itemCount = count
            },
            onFailure = {
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick () }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(0xFFF5F5F5)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(Color(0xFFE0E0E0), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.List,
                    contentDescription = "List Icon",
                    tint = Color(0xFF757575),
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.weight(1f)
            ) {
                InfoRow(icon = Icons.Default.Info, text = name)
                InfoRow(icon = null, text = description)
                InfoRow(
                    icon = Icons.AutoMirrored.Filled.List,
                    text = "Items: $itemCount"
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Box(contentAlignment = Alignment.TopEnd) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                    modifier = Modifier
                        .clickable { menuExpanded = true }
                        .padding(8.dp)
                )
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false },
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    DropdownMenuItem(
                        text = { Text("Add new items") },
                        onClick = {
                            navController.navigate("addNewItem/$id")
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Edit") },
                        onClick = {
                            navController.navigate("editList/$id")
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Share this list") },
                        onClick = {
                            navController.navigate("shareList/$id")
                            menuExpanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Delete") },
                        onClick = {
                            navController.navigate("deleteList/$id")
                            menuExpanded = false
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListCard(){
    ListCard(navController = rememberNavController(), id = "1", name = "Lista de Compras",
        description = "Lista de compras do mês", onClick = {})
}