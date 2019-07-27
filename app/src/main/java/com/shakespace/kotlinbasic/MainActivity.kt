package com.shakespace.kotlinbasic

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Base64
import kotlinx.coroutines.*
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GlobalScope.launch {
            delay(2000)
        }

        job = Job()

        testBase64()

    }

    private fun testBase64() {
        val instance = KeyGenerator.getInstance("DES")

        //  des only support 64
        instance.init(64)

        // create key
        val secretKey = instance.generateKey()
        //  encode to bytes
        val bytes = secretKey.encoded

        // change to string
        val string = Base64.encodeToString(bytes, Base64.DEFAULT)

        println(bytes!!.contentToString())
        println(string)


        //  decode to bytes
        val decode = Base64.decode(string, Base64.DEFAULT)
        // create keySpec by decode bytes
        val keySpec = SecretKeySpec(decode, "DES")


        val keyFactory = SecretKeyFactory.getInstance("DES")
        //  get secret  by keySpec
        val secret = keyFactory.generateSecret(keySpec)
        val encoded = secret.encoded

        println(encoded!!.contentToString())

    }

    fun test(age: Int, name: String, desc: String) {}

    fun foo(): Int { // 这是不好的风格
        return 1
    }

    fun foo2() = 1 // 这是好的风格

    fun doSomething() {
// 启动 10 个协程, 每个工作一段不同长度的时间
        repeat(10) { i ->
            launch {
                delay((i + 1) * 200L) // 分别延迟 200ms, 400ms, ... 等等
                println("Coroutine $i is done")
            }
        }
    }

    fun destroy() {
        job.cancel()
    }
}
