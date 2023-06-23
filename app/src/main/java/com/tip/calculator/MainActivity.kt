package com.tip.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.tip.calculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.jvm.internal.Intrinsics.Kotlin

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            calculateTip()
        }


    }

    private fun calculateTip(){
        val cost = binding.etCostOfService.text.toString().toDoubleOrNull()
        if(cost == null){
            binding.tvTipAmount.text = "NULL"
            return
        }

        val tipPercentage = when(binding.rgTipOptions.checkedRadioButtonId){
            R.id.rbAmazing -> 0.20
            R.id.rbGood -> 0.18
            else -> 0.15
        }

        var finalCost = cost *tipPercentage

        if(binding.switchRoundTip.isChecked){
            finalCost = kotlin.math.ceil(finalCost)
        }

        val formattedVal = NumberFormat.getCurrencyInstance().format(finalCost)

        binding.tvTipAmount.setText("Tip Amount $formattedVal")
    }
}