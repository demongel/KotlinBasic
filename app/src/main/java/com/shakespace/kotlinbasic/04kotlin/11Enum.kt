package com.shakespace.kotlinbasic.`04kotlin`

import android.os.Build
import android.support.annotation.RequiresApi
import java.util.function.BinaryOperator
import java.util.function.IntBinaryOperator

/**
 * 枚举类也可以实现接口 (但不能继承其他类), 可以对所有的枚举常数提供唯一的接口实现, 也可以在不同的枚举常数的匿名类中提供不同 的实现.
 * 枚举类实现接口时, 只需要在枚举类的声明中加入接口名,
 */
fun main() {


}


enum class ProtocolState {
    WAITING {
        override fun signal() = TALKING
    },
    TALKING {
        override fun signal() = WAITING
    };

    abstract fun signal(): ProtocolState
}

@RequiresApi(Build.VERSION_CODES.N)
enum class IntArithmetics : BinaryOperator<Int>, IntBinaryOperator {
    PLUS {
        override fun apply(t: Int, u: Int): Int = t + u
    },
    TIMES {
        override fun apply(t: Int, u: Int): Int = t * u
    };

    override fun applyAsInt(t: Int, u: Int) = apply(t, u)
}
