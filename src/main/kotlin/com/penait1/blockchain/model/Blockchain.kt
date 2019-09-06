package com.penait1.blockchain.model

import com.penait1.blockchain.chain.BlockchainConfig
import java.math.BigInteger
import java.util.*

class Blockchain private constructor(
    private val blocks: MutableList<Block>,
    private val config: BlockchainConfig,
    private val subscriptions: MutableMap<UUID, (Block) -> Unit> = mutableMapOf()
) {

    fun lastBlock() = blocks.last()

    fun addBlock(block: Block): Boolean {
        if (BigInteger(block.hash()) < difficulty()
            && blocks.last().hash().contentEquals(block.previous)
        ) {
            this.blocks.add(block)
            notifySubscribers(block)
            return true
        }
        return false
    }

    fun subscribe(callback: (Block) -> Unit): UUID {
        val id = UUID.randomUUID()
        subscriptions[id] = callback
        return id
    }

    fun unsubscribe(id: UUID) {
        subscriptions.remove(id)
    }

    fun blockHeight() = this.blocks.size

    private fun difficulty() = BigInteger("-57895600000000000000000000000000000000000000000000000000000000000000000000000")

    private fun notifySubscribers(block: Block) {
        subscriptions.forEach {
            it.value.invoke(block)
        }
    }

    companion object {
        fun of(config: BlockchainConfig) = Blockchain(mutableListOf(config.genesisBlock), config)
    }
}
