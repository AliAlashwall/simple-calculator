package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var buttonPlus :Button
    lateinit var buttonMinus :Button
    lateinit var buttonDiv :Button
    lateinit var buttonMul :Button
    lateinit var buttonC : Button
    lateinit var buttonResult :Button
    lateinit var buttonPlusMinus : Button
    lateinit var textNumber : TextView
    lateinit var buttonRem :Button
    var lastNumber :Double = 0.0
    var currentOperation :Operation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        addCallBack()
    }
    private fun initView(){
        buttonPlus = findViewById(R.id.Button_Plus)
        buttonMinus =  findViewById(R.id.Button_minus)
        buttonDiv = findViewById(R.id.Button_Div)
        buttonMul = findViewById(R.id.Button_Mul)
        buttonResult = findViewById(R.id.Button_Result)
        buttonC = findViewById(R.id.Button_C)
        buttonPlusMinus = findViewById(R.id.Button_Plus_Minus)
        buttonRem = findViewById(R.id.Button_Rem)
        textNumber = findViewById(R.id.textView2)
    }
    private fun clearInput(){
        textNumber.text = ""
    }
    private fun addCallBack(){
        buttonC.setOnClickListener{
            clearInput()
        }
        buttonPlus.setOnClickListener{
            prepareOperation(Operation.Plus)
        }
        buttonMinus.setOnClickListener{
            textNumber.text = "-"
            prepareOperation(Operation.Minus)
        }
        buttonDiv.setOnClickListener{
            prepareOperation(Operation.Div)
        }
        buttonMul.setOnClickListener{
            prepareOperation(Operation.Mul)
        }
        buttonResult.setOnClickListener{
            if (checkValidity()){
                val result = doOperation()
                textNumber.text = result.toString()
            }
        }
        buttonPlusMinus.setOnClickListener{
            val num = textNumber.text.toString().toDouble()
            if(num != 0.0){
                textNumber.text= (num * -1).toString()
            }
        }
        buttonRem.setOnClickListener{
            prepareOperation(Operation.Rem)
        }
    }
    private fun prepareOperation(operation: Operation) {
        if (checkValidity()){
            lastNumber = textNumber.text.toString().toDouble()
            clearInput()
            currentOperation = operation
        }
    }
    private fun doOperation() :Double{
        val currentNumber = textNumber.text.toString().toDouble()
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
        val currentDigits = textNumber.text.toString()
        val digits = currentDigits + newDigit
        textNumber.text = digits
    }
    private fun checkValidity():Boolean{
        return textNumber.text != "" && textNumber.text != "-"
    }
}