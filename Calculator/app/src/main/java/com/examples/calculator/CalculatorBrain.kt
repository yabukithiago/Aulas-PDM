package com.examples.calculator

class CalculatorBrain{
    enum class Operation(op: String){
        SUM("+"),
        SUB("-"),
        MULT("*"),
        DIV("/"),
        SQRT(""),
        RAND(""),
        PERCENT("÷");

        fun getOp(value: String) : Operation{
            return when(value){
                SUM.toString() -> SUM
                SUB.toString() -> SUB
                MULT.toString() -> MULT
                DIV.toString() -> DIV
                SQRT.toString() -> SQRT
                PERCENT.toString() -> PERCENT
                RAND.toString() -> RAND
                else -> {
                    RAND
                }
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