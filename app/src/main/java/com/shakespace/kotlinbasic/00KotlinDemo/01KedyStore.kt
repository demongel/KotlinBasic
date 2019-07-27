package com.shakespace.kotlinbasic.`00KotlinDemo`

import android.util.Base64
import javax.crypto.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.SecretKeySpec


fun main() {

    testBase64()

}


private fun testBase64() {
    val instance = KeyGenerator.getInstance("DES")

    instance.init(56)

    val secretKey = instance.generateKey()

    val bytes = secretKey.encoded

    val string = Base64.encodeToString(bytes, Base64.DEFAULT)

    println(string)


    val decode = Base64.decode(string, Base64.DEFAULT)
    val keySpec = SecretKeySpec(decode, "DES")

    val keyFactory = SecretKeyFactory.getInstance("DES")
    val secret = keyFactory.generateSecret(keySpec)
    val encoded = secret.encoded

    println(encoded!!.contentToString())

}