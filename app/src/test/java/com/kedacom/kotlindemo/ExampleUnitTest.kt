package com.kedacom.kotlindemo

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    var a = 123
    val s = "hello $a"

    var age: Int? = 12

    @Test
    fun test() {

        a = 2
        print(s)

        println()

        a = 112333
        val s2 = "${s.replace("hello", "bonjo")}kjkjkjkj $a"
        print(s2)
    }


    @Test
    fun stepp() {
//        for (i in 1..4) print(i)
//        for(i in 4 downTo 1)print(i)
//        for (i in 1..10 step 3) print(i)
//        for(i in 1 until 10)print(i)

        val str = "helloworld"
        for (i in str) println(i)

    }

    @Test
    fun condition() {
        val a = 123
        val b = 2323

        val c = if (a > b) a else b
        println(c)

        val x = 200
        println((x in a..b))
    }
}
