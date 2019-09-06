package com.penait1.blockchain.miner

import com.penait1.blockchain.model.Block
import com.penait1.blockchain.model.Blockchain
import java.math.BigInteger
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class Miner(
    private val blockchain: Blockchain
) {

    private var nonce = 0L
    private var newBlock: Block = Block.new(blockchain.lastBlock().hash(), Any(), nonce.toString())

    private fun latestBlock(block: Block) {
        println(SimpleDateFormat("hh:mm:ss").format(block.timestamp) + ": Block added to blockchain")
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
    }
}
