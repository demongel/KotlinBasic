package com.shakespace.kotlinbasic.`06kotlinFunc`

/**
为了在类型和参数声明中处理函数, 比如: val onClick: () -> Unit = ... , Kotlin 使用了一系列的函数类型(Function Type), 比如 (Int) -> String .
这种函数类型使用一种特殊的表示方法, 用于表示函数的签名部分, 也就是说, 用于表示函数的参数和返回值:
— 所有的函数类型都带有参数类型列表, 用括号括起, 以及返回值类型: (A, B) -> C 表示一个函数类型, 它接受两个参数, 类型为 A 和
B , 返回值类型为 C . 参数类型列表可以为空, 比如 () -> A . Unit 类型的返回值 不能省略.
— 函数类型也可以带一个额外的 接受者 类型, 以点号标记, 放在函数类型声明的前部: A.(B) -> C 表示一个可以对类型为 A 的接受者
调用的函数,参数类型为B,返回值类型为 C.对这种函数类型,我们经常使用带接受者的函数字面值. —挂起函数(Suspendingfunction)是一种特殊类型的函数,它的声明带有一个特殊的suspend修饰符,比如: suspend()->Unit,或者:
suspend A.(B) -> C .
函数类型的声明也可以指定函数参数的名称: (x: Int, y: Int) -> Point . 参数名称可以用来更好地说明参数含义.
你也可以使用 类型别名 来给函数类型指定一个名称: typealias ClickHandler = (Button, ClickEvent) -> Unit


使用函数字面值, 采用以下形式之一:
—Lambda表达式: {a,b->a+b} ,
—匿名函数(AnonymousFunction): fun(s:String):Int{returns.toIntOrNull()?:0}
带接受者的函数字面值 可以用作带接受者的函数类型的实例.
— 使用已声明的元素的可调用的引用:
— 顶级函数, 局部函数, 成员函数, 或扩展函数, 比如: ::isOdd , String::toInt ,
— 顶级属性, 成员属性, 或扩展属性, 比如: List<Int>::size ,
—构造器,比如: ::Regex
以上几种形式都包括绑定到实例的可调用的引用,也就是指向具体实例的成员的引用: foo::toString.
— 使用自定义类, 以接口的方式实现函数类型:
如果有足够的信息, 编译器可以推断出变量的函数类型:

要调用一个函数类型的值, 可以使用它的 invoke(...) 操作符: f.invoke(x) , 或者直接写 f(x) .


Lambda 表达式包含在大括号之内, 在完整语法形式中,
参数声明在大括号之内, 参数类型的声明是可选的, 函数体在 -> 符号之后.
如果 Lambda 表达式自动推断的返回值类型不是 Unit ,
那么 Lambda 表达式函数体中, 最后一条(或者就是唯一一条)表达式的值, 会被当作整 个 Lambda 表达式的返回值.


noinline
如果一个内联函数的参数中有多个 Lambda 表达式, 而你只希望内联其中的一部分, 你可以对函数的一部分参数添加 noinline 标记:
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) { ... }

 */
fun main() {

}

val sum: Int.(Int) -> Int = { other -> plus(other) }