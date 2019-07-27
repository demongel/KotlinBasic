package com.shakespace.kotlinbasic.`03Kotlin1`.`3`

/**
 *  函数类型 放宽到44个？
 */

fun main() {


//    fun Request.getBody() =
//        when (val response = executeRequest()) {
//            is Success -> response.body
//            is HttpError -> throw HttpException(response.status)
//        }

}

interface Foo {
    companion object {
        @JvmField
        val answer: Int = 42

        @JvmStatic
        fun sayHello() {
            println("Hello, world!")
        }
    }
}