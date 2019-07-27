package com.shakespace.kotlinbasic.`05kotlinother`

/**
 * 在 Java 平台上, 使用反射功能所需要的运行时组件是作为一个单独的 JAR 文件发布的( kotlin-reflect.jar).
 * 这是为了对那些不使 用反射功能的应用程序, 减少其运行库的大小.
 * 如果你需要使用反射, 请注意将这个 .jar 文件添加到你的项目的 classpath 中.
 */

val x = 1

fun main() {

    //表达式 ::x 的计算结果是一个属性对象, 类型为 KProperty<Int> ,
    // 通过它 get() 方法可以得到属性值,
    // 通过它的 name 属性可以得到属 性名称.
    println(::x.get())
    println(::x.name)

    val annotations = Anno::class.java.annotations

    annotations.forEach {
        println(it)
    }
}

/**
— @Target 指定这个注解可被用于哪些元素(类, 函数, 属性, 表达式, 等等.);
— @Retention 指定这个注解的信息是否被保存到编译后的 class 文件中, 以及在运行时是否可以通过反射访问到它(默认情况下, 这两个 设定都是 true);
— @Repeatable 允许在单个元素上多次使用同一个注解;
— @MustBeDocumented 表示这个注解是公开 API 的一部分, 在自动产生的 API 文档的类或者函数签名中, 应该包含这个注解的信息.
 */
@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.EXPRESSION
)
@Retention(AnnotationRetention.SOURCE)
@MustBeDocumented
annotation class Fancy

/**
注解可以拥有带参数的构造器.
允许使用的参数类型包括:
— 与 Java 基本类型对应的数据类型(Int, Long, 等等.); — 字符串;
— 类 ( Foo::class );
— 枚举;
— 其他注解;
— 由以上数据类型构成的数组.
注解的参数不能是可为 null 的类型, 因为 JVM 不支持在注解的属性中保存 null 值. 如果一个注解被用作另一个注解的参数, 那么在它的名字之前不使用 @ 前缀:

Kotlin 支持的所有注解使用目标如下:
— file;
— property (使用这个目标的注解, 在 Java 中无法访问);
— field ;
— get (属性的 get 方法);
— set (属性的 set 方法);
— receiver (扩展函数或扩展属性的接受者参数);
— param (构造器的参数);
— setparam (属性 set 方法的参数);
— delegate (保存代理属性的代理对象实例的域变量).



 */

@Fancy
data class Anno(val name: String)