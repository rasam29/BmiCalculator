package com.github.rasam29.bmicalculator.test


object Red: Appearance {
    override fun getColor(): String {
        return "Red"
    }
}

object SixSeat: Interior {
    override fun getNumberOfSeats(): Int {
        return 6
    }
}

object Slow: Performance {
    override fun getMaxSpeed(): Int {
        return 160;
    }

}

interface Appearance{
    fun getColor(): String
}
interface Interior{
    fun getNumberOfSeats(): Int
}
interface Performance{
    fun getMaxSpeed(): Int
}



class MyCar :Appearance by Red,Interior by SixSeat, Performance by Slow

