package com.shakespace.kotlinbasic.`04kotlin`

import android.icu.util.ValueIterator

/**
 * 属性声明
 * 次构函数
 * 方法声明
 * 伴生对象
 *
 * 将关系紧密到方法放在一起
 *
 * 嵌套类放在使用它到代码之后
 *
 * 包名不使用下划线
 * 多单词 使用驼峰
 *
 * 函数 使用驼峰
 *
 * 测试方法  使用反引号？？？
 *
 * 类名：名词  名词短语
 * 方法：动词 动词短语
 *
 * 在名称中使用缩写字母时, 如果缩写字母只包含两个字母, 请将它们全部大写
 * (比如 IOStream ); 如果超过两个字母, 请将首字母大写, 其 他字母小写 (比如 XmlFormatter , HttpInputStream ).
 *
 *
 * 缩进时使用 4 个空格. 不要使用 tab.
 *
 * 二元运算符前后应该加入空格 ( a + b ).
 * 例外情况是: 不要在 “值范围” 运算符前后加入空格 ( 0..i ).
 * 一元运算符前后不要加入空格 ( a++ )
 * 流程控制关键字( if , when , for 以及 while ) 以及对应的开括号之间, 要加入空格.
 * 对于主构造器声明, 方法声明, 以及方法调用, 不要在开括号之前加入空格.
 *
 * 不要在 (, [ 之后加入空格,也不要在 ], ) 之前加入空格.
 * 不要在 . 或 ?. 前后加入空格: foo.bar().filter { it > 2 }.joinToString() , foo?.bar()
 * 在 // 之后加入空格: // This is a comment
 * 对于用来表示类型参数的尖括号,不要在它前后加入空格: classMap<K,V>{...}
 * 不要在 :: 前后加入空格: Foo::class , String::length
 * 对于用来表示可空类型的 ? , 不要在它之前加入空格: String?
 * 一般来说, 不要进行任何形式的水平对其. 如果将一个标识符改为不同长度的名称, 不应该影响到它的任何声明, 以及任何使用的格式.
 *
 *对链式调用(chained call)换行时, 将 . 字符或 ?. 操作符放在下一行, 使用单倍缩进:
 *
 *在 Lambda 表达式中返回
 * 不要在 Lambda 表达式中使用多个带标签的返回. 应该考虑重构你的 Lambda 表达式, 使它只有一个退出点. 如果无法做到, 或者代码不够 清晰, 那么可以考虑把 Lambda 改为一个匿名函数.
 * 在 Lambda 表达式中, 不要使用带标签的返回最为最后一条语句.
 *
 * 尽量使用 try , if 以及 when 的表达式形式.
 *
 * 如果存在三个或更多的条件分支,尽量使用 when.
 *
 *如果需要在条件语句中使用可为空的 Boolean , 请使用 if (value == true) 或者 if (value == false) 进行判断.
 *
 * 数值范围是一个开区间(不包含其末尾元素), 那么应该使用 until 函数进行循环
 *  for (i in 0..n - 1) { ... } // 不好的风格
 *  for (i in 0 until n) { ... } // 比较好的风格
 *
 *
 *  当以下条件成立时, 应该选择使用只读属性, 而不是使用函数:
 *  — 不会抛出异常
 *  — 计算过程消费的资源不多(或者在初次运行时缓存了计算结果)
 *  — 对象状态没有发生变化时, 多次调用会返回相同的结果
 *
 *应该尽量多的使用扩展函数. 如果你的某个函数主要是为某个对象服务,
 * 应该考虑将它转变为这个对象的一个扩展函数. 为了尽量减小 API 污染,
 * 应该将扩展函数的可见度尽量限制在合理的程度. 如果需要, 尽量使用局部扩展函数,
 * 成员扩展函数, 或者可见度为 private 的顶级扩 展函数.
 *
 *如果一个函数服务于两个参数, 而且这两个参数的角色很类似,
 * 只有这种情况下才应该将函数声明为中缀函数.
 * 好的例子比如: and , to , zip.坏的例子比如: add.
 *
 *
 *
 *
 *
 *
 *
 */
fun main() {

}

/**
 * 如果类拥有两个属性, 它们在概念上是相同的, 但其中一个是公开 API 的一部分,
 * 而另一个属于内部的实现细节, 此时请使用下划线作为私 有属性名的前缀:
 */
class C {
    private val _elementList = mutableListOf<ValueIterator.Element>()
    val elementList: List<ValueIterator.Element> get() = _elementList
}

//如果类的头部很长, 应该调整代码格式, 将主构造器(primary constructor)的每一个参数放在单独的行中, 并对其缩进. 同时, 闭括号也应放
//在新的一行. 如果我们用到了类的继承, 那么对超类构造器的调用, 以及实现的接口的列表, 应该与闭括号放在同一行内:

//class Person( id: Int,
//              name: String,
//              surname: String
//) : Human(id, name) { ... }

/**
public / protected / private / internal
expect / actual
final / open / abstract / sealed / const
external
override
lateinit
tailrec
vararg
suspend
inner
enum / annotation
companion
inline
infix
operator
data
所有的注解要放在修饰符之前:
除非你在开发一个库,否则应该省略多余的修饰符(比如 public).
 */

/**
 *
在多行的 Lambda 表达式中声明参数名称时, 请将参数名放在第一行, 后面放箭头, 然后换行:
如果参数列表太长, 无法放在一行之内, 请将箭头放在单独的一行:
文档注释
对于比较长的文档注释,请将开头的 /** 放在单独的行,后面的每一行都用星号开始:
比较短的注释可以放在一行之内:
/** 这是一段比较短的文档注释. */
通常来说, 不要使用 @param 和 @return 标记. 相反, 对参数和返回值的描述应该直接合并到文档注释之内, 在提到参数的地方应该添 加链接. 只有参数或返回值需要很长的解释, 无法写在文档注释中, 这时才应该使用 @param 和 @return 标记.
fun foo() { ints.forEach lit@{
// ...
} }
appendCommaSeparated(properties) { prop -> val propertyValue = prop.get(obj) // ...
}
foo {
context: Context,
environment: Env
-> context.configureEnv(environment)
}
*/
 */

// 不要写这样的注释:
/**
 * 对于给定的数值, 返回其绝对值.
 * @param number 需要返回绝对值的对象数值. * @return 绝对值.
 */
//  should like this
/**
 * for certain [number],print number
 */
fun get(number: Int) {}


// 这是不好的风格: 对于内容不再变化的值, 使用了可变的集合类型
fun validateValue(actualValue: String, allowedValues: HashSet<String>) {
//    ...
} // 这是比较好的风格: 改用了不可变的集合类型

fun validateValue(actualValue: String, allowedValues: Set<String>) {
//    ...
}

// 这是不好的风格: arrayListOf() 的返回类型为 ArrayList<T>, 这是一个可变的集合类型
val allowedValues = arrayListOf("a", "b", "c")
// 这是比较好的风格: listOf() 的返回类型为 List<T>
val allowedValues2 = listOf("a", "b", "c")


//工厂函数
//如果你为一个类声明一个工厂方法, 请不要使用与类相同的名称. 尽量使用一个不同的名称, 解释清楚工厂函数的行为有什么不同之处. 只 有当工厂函数的确实不存在什么特殊意义的时候, 这时你才可以使用与类相同的名称作为函数名.
//示例:
//class Point(val x: Double, val y: Double) {
//    companion object {
//        fun fromPolar(angle: Double, radius: Double) = Point(//)
//    }
//
//}
//如果某个对象拥有多个不同参数的重载构造器, 这些构造器不会调用超类中的不同的构造器, 而且无法缩减成带默认值参数的单个构造器, 这时应该将这些构造器改为工厂函数.


