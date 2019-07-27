package com.shakespace.kotlinbasic.`05kotlinother`

/**

Try 是一个表达式
try 是一个表达式, 也就是说, 它可以有返回值:
val a: Int? = try { parseInt(input) } catch (e: NumberFormatException) { null }
try 表达式的返回值, 要么是 try 代码段内最后一个表达式的值, 要么是 catch 代码段内最后一个表达式的值. finally 代码段的内容不会影 响 try 表达式的结果值.


如果你调用这个函数, 编译器就会知道, 执行到这个调用时, 程序就会停止:
另一种用到这个类型的情况是类型推断. 这个类型的可为 null 的变量, Nothing? , 只有唯一一个可能的值, 就是 null . 如果对一个自动推 断类型的值, 使用 null 来初始化, 而且又没有更多的信息可以用来推断出更加具体的类型, 编译器会将类型推断为 Nothing? :
与 Java 的互操作性
关于与 Java 的互操作性问题, 请参见 与 Java 的互操作性 中关于异常的小节.


fun fail(message: String): Nothing {
throw IllegalArgumentException(message)
}
 */