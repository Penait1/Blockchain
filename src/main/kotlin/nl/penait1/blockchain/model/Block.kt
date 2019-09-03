package main.kotlin.nl.penait1.blockchain.model

import java.security.MessageDigest

data class Block(
    val index: Int,
    val timestamp: Long,
    val data: Object,
    val previousHash: ByteArray,
    val nonce: String,
    val currentHash: ByteArray
) {
    companion object {
        fun new(
            index: Int,
            data: Object,
            previousHash: ByteArray,
            nonce: String
        ): Block {
            val timestamp = System.currentTimeMillis()
            val currentHash = blockContentToHash(index, timestamp, data, previousHash, nonce)
            return Block(index, timestamp, data, previousHash, nonce, currentHash)
        }

        fun validate(block: Block) = calculateHashFromBlock(block) == block.currentHash

        fun calculateHashFromBlock(block: Block) =
            blockContentToHash(block.index, block.timestamp, block.data, block.previousHash, block.nonce)

        private fun blockContentToHash(
            index: Int,
            timestamp: Long,
            data: Object,
            previousHash: ByteArray,
            nonce: String
        ): ByteArray {
            val md = MessageDigest.getInstance("SHA-256")
            return md.digest(
                index.toString().toByteArray() + timestamp.toString().toByteArray() + data.toString().toByteArray() + previousHash
                        + nonce.toByteArray()
            )
        }
    }
}
