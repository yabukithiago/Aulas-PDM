package com.examples.shoppinglist.ui.login

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
)
class LoginViewModel : ViewModel() {
    var state = mutableStateOf(LoginState())
        private set

    private val email
        get() = state.value.email
    private val password
        get() = state.value.password

    fun onEmailChange(newValue: String) {
        state.value = state.value.copy(email = newValue)
    }

    fun onPasswordChange(newValue: String) {
        state.value = state.value.copy(password = newValue)
    }

    fun login(onLoginSuccess: () -> Unit) {
        val auth = FirebaseAuth.getInstance()
        state.value = state.value.copy(isLoading = true)

        if (email.isEmpty()) {
            state.value = state.value.copy(errorMessage = "Email não pode ser vazio")
            return
        }

        if (password.isEmpty()) {
            state.value = state.value.copy(errorMessage = "Senha não pode ser vazia")
            return
        }
        auth.signInWithEmailAndPassword(state.value.email, state.value.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    onLoginSuccess()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                }
            }
    }

    fun logout(onLogoutSuccess: () -> Unit) {
        FirebaseAuth.getInstance().signOut()
        onLogoutSuccess()
    }
}