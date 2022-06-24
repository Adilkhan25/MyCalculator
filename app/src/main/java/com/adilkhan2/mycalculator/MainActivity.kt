package com.adilkhan2.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextSwitcher
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private  var textView : TextView? = null
    private  var isDecimal : Boolean = false;
    private  var isDigit : Boolean = false
    private  var split : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView = findViewById((R.id.textView))
    }
    fun onDigit(view : View)
    {

        textView?.append((view as Button).text)
        isDigit = true;

    }
    fun onClear(view: View)
    {
        textView?.text = ""
        isDigit = false
        isDecimal = false;
    }

    fun onDecimal(view: View) {
           if(!isDecimal && isDigit)
           {

               textView?.append((view as Button).text)
               isDecimal = true

           }
    }
    fun onOperator(view: View)
    {
        textView?.text?.let {
            if(isDigit && !isOperatorAdd(it.toString()))
            {
                textView?.append((view as Button).text)
                isDecimal = false
                isDigit = false
            }
        }
    }
    private fun isOperatorAdd(value: String) : Boolean
    {
        return if(value.startsWith("-")) {
            false
        }
        else
        {
                    value.contains("+") ||
                    value.contains("-") ||
                    value.contains("/") ||
                    value.contains("*")
        }
    }

    fun onAnswer(view: View) {
       if(isDigit)
       {
           var theValue = textView?.text.toString()
           var prefix = ""
           try {


               if (theValue.startsWith("-")) {
                   prefix += "-"
                   theValue = theValue.substring(1)

               }
               if (theValue.contains("-")) {
                   var splitValue = theValue.split(("-"))
                   var one = splitValue[0]
                   var two = splitValue[1]
                   if (prefix.isNotEmpty()) {
                       one = prefix + one
                   }
                   textView?.text = (one.toDouble() - two.toDouble()).toString()
               }
               if (theValue.contains("+")) {
                   var splitValue = theValue.split(("+"))
                   var one = splitValue[0]
                   var two = splitValue[1]
                   if (prefix.isNotEmpty()) {
                       one = prefix + one
                   }
                   textView?.text = (one.toDouble() + two.toDouble()).toString()
               }
               if (theValue.contains("*")) {
                   var splitValue = theValue.split(("*"))
                   var one = splitValue[0]
                   var two = splitValue[1]
                   if (prefix.isNotEmpty()) {
                       one = prefix + one
                   }
                   textView?.text = ((one.toDouble())*(two.toDouble())).toString()
               }
               if (theValue.contains("/")) {
                   var splitValue = theValue.split(("/"))
                   var one = splitValue[0]
                   var two = splitValue[1]
                   if (prefix.isNotEmpty()) {
                       one = prefix + one
                   }
                   textView?.text = ((one.toDouble())/(two.toDouble())).toString()
               }

           }

           catch (e:ArithmeticException)
           {
               e.printStackTrace()
           }

       }
    }

}