    import android.icu.util.Calendar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun Greet(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier =
        modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var name = ""
        
        TextField(value = "Name", onValueChange = { newValue ->
            print(newValue)
        })
        Button(onClick = { },
            content = { Text(text = "Greet") })
    }
    Text("Hello! $name!")
}

fun Date.longDateToString() : String {
    val format = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
    return format.format(this)
}

fun Date.myNumber() : Int {
    val calendar = Calendar.getInstance()
    calendar.time = this

    return calendar.get(Calendar.DAY_OF_YEAR)
}
fun sum(a: Int, b: Int): Int {
    return a + b
}

@Preview(showBackground = true)
@Composable
fun GreetPreview() {
    Greet("Thiago")
}