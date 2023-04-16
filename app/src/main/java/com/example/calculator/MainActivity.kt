package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    var lastNumber :Double = 0.0
    var currentOperation :Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addCallBack()
    }
    private fun clearInput(){
        binding.textView2.text = ""
    }
    private fun addCallBack(){
        binding.ButtonC.setOnClickListener{
            clearInput()
        }
        binding.ButtonPlus.setOnClickListener{
            prepareOperation(Operation.Plus)
        }
        binding.ButtonMinus.setOnClickListener{
            binding.textView2.text = "-"
            prepareOperation(Operation.Minus)
        }
        binding.ButtonDiv.setOnClickListener{
            prepareOperation(Operation.Div)
        }
        binding.ButtonMul.setOnClickListener{
            prepareOperation(Operation.Mul)
        }
        binding.ButtonResult.setOnClickListener{
            if (checkValidity()){
                val result = doOperation()
                binding.textView2.text = result.toString()
            }
        }
        binding.ButtonPlusMinus.setOnClickListener{
            val num = binding.textView2.text.toString().toDouble()
            if(num != 0.0){
                binding.textView2.text= (num * -1).toString()
            }
        }
        binding.ButtonRem.setOnClickListener{
            prepareOperation(Operation.Rem)
        }
    }
    private fun prepareOperation(operation: Operation) {
        if (checkValidity()){
            lastNumber = binding.textView2.text.toString().toDouble()
            clearInput()
            currentOperation = operation
        }
    }
    private fun doOperation() :Double{
        val currentNumber = binding.textView2.text.toString().toDouble()
        return when(currentOperation){
            Operation.Plus -> lastNumber + currentNumber
            Operation.Minus -> lastNumber - currentNumber
            Operation.Div -> lastNumber / currentNumber
            Operation.Mul -> lastNumber * currentNumber
            Operation.Rem -> lastNumber % currentNumber
            else -> 0.0
        }
    }
    fun onClick(v: View){
        val newDigit = (v as Button).text.toString()
        val currentDigits = binding.textView2.text.toString()
        val digits = currentDigits + newDigit
        binding.textView2.text = digits
    }
    private fun checkValidity():Boolean{
        return binding.textView2.text != "" && binding.textView2.text != "-"
    }
}