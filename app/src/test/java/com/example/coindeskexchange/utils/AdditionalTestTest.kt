package com.example.coindeskexchange.utils

import com.example.coindeskexchange.utils.AdditionalTest.fibonacciTest
import com.example.coindeskexchange.utils.AdditionalTest.generateFibonacci
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


}