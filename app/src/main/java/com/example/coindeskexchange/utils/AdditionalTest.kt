package com.example.coindeskexchange.utils

import kotlin.math.sqrt

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

    fun getFib(n:Int):ArrayList<Int> {
        if (n == 0 || n<=1){
            return arrayListOf(n)
        }

        val fiboList = arrayListOf<Int>()
        var a = 0
        var b = 1
        for (i in 0 until n) {
            fiboList.add(a)
            val c = a+b
            a = b
            b = c
        }
        return fiboList
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

    fun generatedPrimeNumbers(min: Int, max: Int): ArrayList<Long> {
        val primeList = arrayListOf<Long>()
        if (min >= max) {
            return ArrayList()
        } else {
            for (i in min..max) {
                if (checkIsPrimeNumber(i)) {
                    primeList.add(i.toLong())
                }
            }
        }
        return primeList
    }

    fun checkIsPrimeNumber(n: Int): Boolean {
        if (n == 1) return false

        if (n == 2) return true

        if (n % 2 == 0) return false

        val root = sqrt(n.toDouble()).toInt()
        var isPrime = true

        for (i in 3..root step 2) {
            if (n % i == 0) {
                isPrime = false
                break
            }
        }
        return isPrime
    }

}