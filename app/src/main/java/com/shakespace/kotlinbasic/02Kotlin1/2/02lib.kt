package com.shakespace.kotlinbasic.`02Kotlin1`.`2`

import android.os.Build
import android.support.annotation.RequiresApi


@RequiresApi(Build.VERSION_CODES.N)
fun main() {

    var items = (1..9).map { it * it }

//    //  slice to chunk
//    val chunkedIntoLists = items.chunked(4)
//    val points3d = items.chunked(3) { (x, y, z) -> Triple(x, y, z) }
//    val windowed = items.windowed(4)
//
//    val slidingAverage = items.windowed(4) { it.average() }
//    val pairwiseDifferences = items.zipWithNext { a, b -> b - a }
////sampleEnd
//    println("items: $items\n")
//    println("chunked into lists: $chunkedIntoLists")
//    println("3D points: $points3d")
//    println("windowed by 4: $windowed")
//    println("sliding average by 4: $slidingAverage")
//    println("pairwise differences: $pairwiseDifferences")



    val items2 = (1..5).toMutableList()
    items2.shuffle()
    println("Shuffled items: $items2")
    items2.replaceAll { it * 2 }
    println("Items doubled: $items2")
    items2.fill(5)
    println("Items filled with 5: $items2")

//    从 Kotlin 1.2 开始, 对一个值为 null 的平台数据类型调用 x.equals(...) 会
//    抛出 NPE 异常 (但 x == ... 不会抛出异常).

}