package com.shakespace.kotlinbasic.`02Kotlin1`.`2`


//// 与平台相关的 API 的预期声明:
//expect fun hello(world: String): String
//
//fun greet() {
//// 通过预期声明定义的 API 可以这样使用: val greeting = hello("multi-platform world") println(greeting)
//}
//
//expect class URL(spec: String) {
//    open fun getHost(): String
//    open fun getPath(): String
//}


fun main() {

    var foo = Foo()
//    foo.late.isIn



}


fun countFirst(s:Any):Int{
    val firstChar = (s as? CharSequence)?.firstOrNull();
    if(firstChar!=null)
        return s.count {it == firstChar}
    return  0
}

class Foo {
    lateinit var late: String

    // 只能在属性引用上使用
    // only call  by  this::
    fun hell() {
        val initialized = this::late.isInitialized
    }

}
