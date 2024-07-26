package com.example.unittesting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.unittesting.ui.theme.UnitTestingTheme
import com.example.unittesting.utils.NetworkResult
import com.example.unittesting.viewmodels.MainViewModel
import com.example.unittesting.viewmodels.MainViewModelFactory

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = (application as MyApplication).productRepository
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))
            .get(MainViewModel::class.java)

        mainViewModel.getProducts()

        mainViewModel.products.observe(this, Observer {
            when(it){
                is NetworkResult.Success -> {
                    Log.d("CHEEZ", it.data.toString())
//                    adapter = ProductAdapter(it.data!!)
//                    recyclerView.adapter = adapter
                }
                is NetworkResult.Error -> {}
                is NetworkResult.Loading -> {}
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UnitTestingTheme {
        Greeting("Android")
    }
}