package com.example.currencyconverter

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.currencyconverter.databinding.ActivityMainBinding
import com.example.currencyconverter.main.MainViewModel
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Handle window insets for edge-to-edge layout
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Observe ViewModel changes
        lifecycleScope.launchWhenStarted {
            mainViewModel.conversion.collect { event ->
                when (event) {
                    is MainViewModel.CurrencyEvent.Success -> {
                        binding.result.text = event.resultText
                    }
                    is MainViewModel.CurrencyEvent.Failure -> {
                        binding.result.text = event.errorText
                    }
                    is MainViewModel.CurrencyEvent.Loading -> {
                        binding.result.text = "Loading..."
                    }
                    is MainViewModel.CurrencyEvent.Empty -> {
                        binding.result.text = ""
                    }
                }
            }
        }

        // Button click listener
        binding.btnConvert.setOnClickListener {
            mainViewModel.convertCurrency(
                binding.etAmt.text.toString(),
                binding.currencyList.selectedItem.toString(),
                binding.currencyList2.selectedItem.toString()
            )
        }
    }
}
