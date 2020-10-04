package com.ahmetduruer.systemengineeringwork1

import java.security.Key
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


object AESUtil {
    fun generateAESKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")

        keyGen.init(256)

        return keyGen.generateKey()
    }

    fun encrypt(stringToEncrypt: String, secret: String): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")

        val decodeKey = Base64.getDecoder().decode(secret)
        val originalKey = SecretKeySpec(decodeKey, 0, decodeKey.size, "AES")

        cipher.init(Cipher.ENCRYPT_MODE, originalKey)

        return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.toByteArray(charset("UTF-8"))))
    }

    fun decrypt(stringToDecrypt: String, secret: Key): String {
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")

        cipher.init(Cipher.DECRYPT_MODE, secret)

        return String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)))
    }
}