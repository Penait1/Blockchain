package com.penait1.blockchain.miner

import com.penait1.blockchain.model.Block
import com.penait1.blockchain.model.Blockchain

class Miner(
    private val blockchain: Blockchain,
    private var lastBlock: Block
) {

    fun setLastBlock(block: Block) {
        lastBlock = block
    }

    fun mine() {
        var nonce = 0
        val block = Block.new(lastBlock.hash(), Any(), nonce.toString())
        while (!blockchain.addBlock(block)) {
            block.nonce = (++nonce).toString()
        }

        println("Block found: " + block.hash())
    }
}
