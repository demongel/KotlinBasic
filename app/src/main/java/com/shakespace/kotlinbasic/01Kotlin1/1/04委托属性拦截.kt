package com.shakespace.kotlinbasic.`01Kotlin1`.`1`


fun main() {

     printAllValues<RGB>()

}

inline fun <reified T:Enum<T>> printAllValues(){
    print(enumValues<T>().joinToString {
        it.name
    })
}

enum class RGB{
    RED,GREEN,BLUE
}