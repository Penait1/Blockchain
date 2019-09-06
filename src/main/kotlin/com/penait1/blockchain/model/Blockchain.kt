package com.penait1.blockchain.model

import com.penait1.blockchain.chain.BlockchainConfig
import java.util.*

class Blockchain private constructor(
    private val blocks: MutableList<Block>,
    private val config: BlockchainConfig,
    private val subscriptions: MutableMap<UUID, (Block) -> Unit> = mutableMapOf()
) {

    fun lastBlock() = blocks.last()

    fun addBlock(block: Block): Boolean {
        if (blocks.last().hash().contentEquals(block.previous)
            && block.hash().toString().startsWith("0".repeat(difficulty()))
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

    //TODO calculate difficulity
    private fun difficulty() = 1

    private fun notifySubscribers(block: Block) {
        subscriptions.forEach {
            it.value.invoke(block)
        }
    }

    companion object {
        fun of(config: BlockchainConfig) = Blockchain(mutableListOf(config.genesisBlock), config)
    }
}
