package com.shakespace.kotlinbasic.`04kotlin`

/**
 *
 * 封闭类(Sealed class)用来表示对类阶层的限制, 可以限定一个值只允许是某些指定的类型之一,
 * 而不允许是其他类型. 感觉上, 封闭类是枚 举类(enum class)的一种扩展:
 * 枚举类的值也是有限的, 但每一个枚举值常数都只存在唯一的一个实例,
 * 封闭类则不同, 它允许的子类类型 是有限的, 但子类可以有多个实例, 每个实例都可以包含它自己的状态数据.
要声明一个封闭类,需要将 sealed 修饰符放在类名之前.
封闭类可以有子类,但所有的子类声明都必须定义在封闭类所在的同一个源代 码文件内.
(在 Kotlin 1.1 之前, 规则更加严格: 所有的子类声明都必须嵌套在封闭类的声明部分之内).

out  T
支持 子类向父类转型 ， 即泛型是Any，可以返回String 或其他子类型
T 只能用在函数的返回值，或者修饰只读权限属性（不可变）

相对于外部而言，协变是生产泛型参数的角色，向外输出 所以是out


in T
修饰可变属性，或者作为参数传入，






 */

fun main() {
    fun eval(expr: Expr): Double = when (expr) {
        is Const -> expr.number
        is Sum -> eval(expr.e1) + eval(expr.e2)
        NotANumber -> Double.NaN
// 不需要 `else` 分支, 因为我们已经覆盖了所有的可能情况
    }
}

//  在同一文件中到子类 对应所有类别
sealed class Expr

data class Const(val number: Double) : Expr()
data class Sum(val e1: Expr, val e2: Expr) : Expr()
object NotANumber : Expr()
//object Error : Expr()

interface Source<out T> {
    abstract fun nextT(): T
}

fun demo(strs: Source<String>) {
    //  只要是String的父类都可以
    val objects: Source<Any> = strs // 这是 OK 的, 因为 T 是一个 out 类型参数 // ...
}


class Array<T>(val size: Int) {
//    fun get(index: Int): T {
//
//    }

    fun set(index: Int, value: T) {}
}


//  多个泛型上界
fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String> where T : CharSequence,
                                                                         T : Comparable<T> {
    return list.filter { it > threshold }.map { it.toString() }
}