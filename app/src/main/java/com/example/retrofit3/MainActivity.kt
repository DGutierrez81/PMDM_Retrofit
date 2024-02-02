package com.example.retrofit3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.rememberImagePainter
import com.example.retrofit3.iu.RetrofitServiceFactory
import com.example.retrofit3.ui.theme.Retrofit3Theme
import kotlinx.coroutines.launch
//Para poder hacer el commit
class MainActivity : ComponentActivity() {
    private var DogsList by mutableStateOf<List<DogsResponse>>(emptyList())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val service = RetrofitServiceFactory.makeRetrofitService()
        lifecycleScope.launch {
            DogsList = service.getDogsByBreeds()
        }

        setContent {
            Retrofit3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting(DogsList )
                }
            }
        }
    }
}

@Composable
fun Greeting(dogs: List<DogsResponse>, modifier: Modifier= Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .verticalScroll(
                rememberScrollState(30000),
                enabled = true,
                reverseScrolling = true
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        for (perro in dogs) {
            /*
            Column {
                Text(text = "ID: ${gato.id}")
                Text(text = "URL: ${gato.url}")
                Text(text = "Width: ${gato.width}")
                Text(text = "Height: ${gato.height}")
            }

             */
                    Image(
                        painter = rememberImagePainter(perro.url) ,
                        contentDescription = null,
                        modifier = Modifier
                            .size(200.dp)
                            .clip(RoundedCornerShape(8.dp)) // redondear las esquinas
                    )
        }
    }
}