package com.example.coindeskexchange.utils

object AdditionalTest {


    fun filterIntersectArray(listOne: ArrayList<Int>, listTwo: ArrayList<Int>): ArrayList<Int> {
        val temp = ArrayList(listOne)
        for (i in listOne) {
            for (j in listTwo) {
                if (i == j) {
                    temp.remove(i)
                }
            }
        }
        temp.forEach {
            listOne.remove(it)
        }
        return listOne
    }

    fun generateFibonacci(n: Int): ArrayList<Long> {
        if (n == 0 || n <= 1) {
            return arrayListOf(n.toLong())
        }
        val fibonacciList = arrayListOf<Long>()
        var a = 0L
        var b = 1L
        for (i in 0 until n) {
            fibonacciList.add(a)
            val c = a+b
            a = b
            b = c
        }
        return fibonacciList
    }

    /**
     * Return the n-th fibonacci number
     * They are defined like this:
     * fib(0) = 0
     * fib(1) = 1
     * fib(n) = fib(n-2) + fib(n-1)
     */

    fun fibonacciTest(n: Int): Long {
        if (n == 0 || n == 1) {
            return n.toLong()
        }
        var a = 0L //1 1
        var b = 1L //1 2
        var c = 1L //2
        for (i in 1 until n) {
            c = a + b //1 2 3
            a = b // a = 1 1 2
            b = c // b = 1 2 3
        }
        return c
    }

}