package com.penait1.blockchain.miner

import com.penait1.blockchain.model.Block
import com.penait1.blockchain.model.Blockchain

class Miner(
    private val blockchain: Blockchain
) {

    private var nonce = 0L
    private var newBlock: Block = Block.new(blockchain.lastBlock().hash(), Any(), nonce.toString())

    private fun latestBlock(block: Block) {
        nonce = 0L
        newBlock = Block.new(block.hash(), Any(), nonce.toString())
    }

    init {
        blockchain.subscribe(this::latestBlock)
    }

    fun mine() {
        while (!blockchain.addBlock(newBlock)) {
            newBlock.nonce = (++nonce).toString()
        }

        println("Block found: " + newBlock.hash())
    }
}
