package com.ahmetduruer.systemengineeringwork1

import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey
import javax.crypto.Cipher

object RSAUtil {
    fun generateRSAKeyPair(): KeyPair {
        val keyGen = KeyPairGenerator.getInstance("RSA")

        keyGen.initialize(1024)

        return keyGen.generateKeyPair()
    }

    fun encrypt(data: String, publicKey: PublicKey): ByteArray {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")

        cipher.init(Cipher.ENCRYPT_MODE, publicKey)

        return cipher.doFinal(data.toByteArray())
    }


    fun decrypt(data: ByteArray, privateKey: PrivateKey): String {
        val cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)

        return String(cipher.doFinal(data))
    }
}