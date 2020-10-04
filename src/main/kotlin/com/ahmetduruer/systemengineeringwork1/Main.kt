package com.ahmetduruer.systemengineeringwork1

import java.security.PublicKey
import java.util.*

class Main {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val alicePublicKeys = mutableMapOf<String, PublicKey>()
            val bobEncryptedKeys = mutableMapOf<String, ByteArray>()
            val aliceMessages = mutableMapOf<String, String>()

            val bobKeyPair = RSAUtil.generateRSAKeyPair() // bob created a asymmetric key

            alicePublicKeys["bob"] = bobKeyPair.public // alice got bob's public key

            val aliceSymmetricKey = AESUtil.generateAESKey() // alice created symmetric key

            val encryptedKey = RSAUtil.encrypt(
                Base64.getEncoder().encodeToString(aliceSymmetricKey.encoded),
                alicePublicKeys["bob"]!!
            ) // alice encrypted her key with bob's public key

            bobEncryptedKeys["alice"] = encryptedKey // alice sent secret key to bob

            // bob decrypts the alice's key
            val decryptedKey = RSAUtil.decrypt(
                bobEncryptedKeys["alice"]!!,
                bobKeyPair.private
            ) // decrypted alice's secret key with bob's private

            val message = "Hello" // message

            val encryptedMessage = AESUtil.encrypt(message, decryptedKey) // bob encrypts his message with her key

            aliceMessages["bob"] = encryptedMessage // bob sent encrypted message with her key to alice

            println(
                "bob's message to alice: " + AESUtil.decrypt(
                    aliceMessages["bob"]!!,
                    aliceSymmetricKey
                )
            ) // print out secret message
        }
    }
}