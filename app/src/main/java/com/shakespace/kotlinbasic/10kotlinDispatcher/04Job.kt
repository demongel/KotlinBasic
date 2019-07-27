package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.Job
import kotlinx.coroutines.runBlocking


fun main() = runBlocking<Unit> {
    //sampleStart
    println("My job is ${coroutineContext[Job]}") //sampleEnd
}