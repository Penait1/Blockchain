package com.penait1.blockchain.node.model

import java.security.MessageDigest

data class Block(
    val previous: ByteArray,
    val timestamp: Long,
    val data: Any,
    var nonce: String
) {

    fun hash() =
        MessageDigest.getInstance("SHA-256")
            .digest(previous + timestamp.toString().toByteArray() + data.toString().toByteArray() + nonce.toByteArray())!!

    companion object {
        fun new(previousHash: ByteArray, data: Any, nonce: String) =
            of(previousHash, data, nonce, System.currentTimeMillis())

        fun of(previousHash: ByteArray, data: Any, nonce: String, timestamp: Long) =
            Block(previousHash, timestamp, data, nonce)
    }
}
