package com.examples.shoppinglist.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.examples.shoppinglist.R
import com.examples.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun LoginView(navController: NavController, modifier: Modifier = Modifier, onLoginSuccess: () -> Unit = {}) {

    val viewModel : LoginViewModel = viewModel()
    val state by viewModel.state

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                modifier = Modifier
                    .width(150.dp)
                    .height(150.dp),
                painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
                contentDescription = "Logo DigiSocial"
            )
            TextField(value = state.email,
                onValueChange = viewModel::onEmailChange,
                placeholder = {
                    Text(text = "email")
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(value = state.password,
                onValueChange = viewModel::onPasswordChange,
                placeholder = {
                    Text(text = "password")
                },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(onClick = { viewModel.login(onLoginSuccess = onLoginSuccess) }) {
                    Text(text = "Login")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = { navController.navigate("register") },
                ) {
                    Text(text = "Registar")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    ShoppingListTheme() {
        LoginView(navController = rememberNavController())
    }
}