package com.example.coindeskexchange.utils

import com.example.coindeskexchange.utils.AdditionalTest.checkIsPrimeNumber
import com.example.coindeskexchange.utils.AdditionalTest.fibonacciTest
import com.example.coindeskexchange.utils.AdditionalTest.generateFibonacci
import com.example.coindeskexchange.utils.AdditionalTest.generatedPrimeNumbers
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class AdditionalTestTest {

    @Test
    fun `list A's element is common to list B`() {
        val firstList = arrayListOf(3, 2, 12, 9, 40, 32, 4)
        val secondList = arrayListOf(4, 7, 3, 9, 2)
        val result = AdditionalTest.filterIntersectArray(
            firstList, secondList
        )
        assertThat(result.toSet()).isEqualTo(firstList.intersect(secondList.toSet()))
    }

    @Test
    fun `list A element is not common to list B list A is EmptyList`() {
        val firstList = arrayListOf(1,2,3)
        val secondList = arrayListOf(4,5,6)
        val result = AdditionalTest.filterIntersectArray(
            firstList, secondList
        )
        assertThat(result).isEqualTo(ArrayList<Int>())
    }

    @Test
    fun `fib(n) = fib(n-2) + fib(n-1)`() {
        val n = 2
        val result = fibonacciTest(n)
        assertThat(result).isEqualTo(fibonacciTest(n-2)+fibonacciTest(n-1))
    }

    @Test
    fun `fibonacciList index n is equal to fib(n-1)`() {
        val n = 6
        val fibList = generateFibonacci(n)
        val fibTest = fibonacciTest(n-1)
        assertThat(fibList[n-1]).isEqualTo(fibTest)
    }

    @Test
    fun `check isPrimeNumber Function if it prime return true`() {
        val n = 3
        val result = checkIsPrimeNumber(n)
        assertThat(result).isTrue()
    }

    @Test
    fun `check isPrimeNumber Function 2 is Prime return true`() {
        val n = 2
        val result = checkIsPrimeNumber(n)
        assertThat(result).isTrue()
    }

    @Test
    fun `check isPrimeNumber Function if it not prime return false`() {
        val n = 4
        val result = checkIsPrimeNumber(n)
        assertThat(result).isFalse()
    }

    @Test
    fun `check generatePrimeList Function is element of the list is all prime number`() {
        val primeList = generatedPrimeNumbers(0,20)
        val result: Boolean = primeList.all { checkIsPrimeNumber(it.toInt()) }
        assertThat(result).isTrue()
    }
}