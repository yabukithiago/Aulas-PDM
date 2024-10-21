package com.examples.calculator

class CalculatorBrain{

    enum class Operation(val op: String){
        SUM("+"),
        SUB("-"),
        MULT("*"),
        DIV("/"),
        SQRT(""),
        RAND("\uD83D\uDE82"),
        PERCENT("÷");

        companion object {
            fun getOp(value: String) : Operation {
                return entries.find { it.op == value } ?: RAND
             }
        }
    }

    var operation : Operation? = null
    var operand : Double = 0.0

    fun doOperation(value: Double) : Double{
        var result = when (operation){
            Operation.SUM -> operand + value
            Operation.SUB -> operand - value
            Operation.MULT -> operand * value
            Operation.DIV -> operand / value
            Operation.SQRT -> TODO()
            Operation.RAND -> TODO()
            Operation.PERCENT -> TODO()
            null -> TODO()
        }
        return result

    }
}