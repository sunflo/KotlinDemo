package com.kedacom.kotlindemo.inout

import java.util.*

/**
 * @author keda
 */
internal class TestInOut {
    fun test() {
        val personArrayList: MutableList<Person> = ArrayList()
        personArrayList.add(Person("aaa", 11))
        personArrayList.add(Worker1("bbb", 12))
        personArrayList.add(Worker2("ccc", 13))
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val personArrayList: MutableList<Person> = ArrayList()
            personArrayList.add(Person("aaa", 11))
            personArrayList.add(Worker1("bbb", 12))
            personArrayList.add(Worker2("ccc", 13))
            val personArrayList1: MutableList<Worker1> = ArrayList()
            personArrayList1.add(Worker1("ddd", 14))
            val personArrayList2: MutableList<Worker2> = ArrayList()
            personArrayList2.add(Worker2("eee", 15))
            setWork(personArrayList)
            setWork(personArrayList1)
            setWork(personArrayList2)
        }

        fun setWork(studentList: List<Person>) {
            for (o in studentList) {
                o?.toWork()
            }
        }

        fun test2() {
            val personArrayList: MutableList<Person> = ArrayList()
            personArrayList.add(Person("aaa", 11))
            personArrayList.add(Worker1("bbb", 12))
            personArrayList.add(Worker2("ccc", 13))
            val personArrayList1: MutableList<Worker1> = ArrayList()
            personArrayList1.add(Worker1("ddd", 14))
            setWorker(personArrayList)
            setWorker(personArrayList1)
        }

        fun setWorker(studentList: MutableList<in Worker1>) {
            for (o in studentList) {
                println("哈哈 $o")
            }
        }
    }
}