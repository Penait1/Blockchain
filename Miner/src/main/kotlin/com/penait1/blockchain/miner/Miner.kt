package com.penait1.blockchain.miner

import com.penait1.blockchain.node.db.LevelDB
import com.penait1.blockchain.node.model.Block
import com.penait1.blockchain.node.model.Blockchain
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    try {
        val blockchain = Blockchain.main()

        val miner = Miner(blockchain)

        val start = Date()
        while (blockchain.blockHeight() < 5) {
            miner.mine()
        }
        println("Average blocktime: ${(Date().time - start.time) / 5}")
    } finally {
        LevelDB.db.close()
    }
}

class Miner(
    private val blockchain: Blockchain
) {

    private var nonce = 0L
    private var newBlock: Block = Block.new(blockchain.lastBlock().hash(), Any(), nonce.toString())

    private fun latestBlock(block: Block) {
        println(SimpleDateFormat("hh:mm:ss").format(block.timestamp) + ": Block added to blockchain")
        println("Resetting nonce: $nonce")
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
