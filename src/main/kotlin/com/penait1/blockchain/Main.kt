package com.penait1.blockchain

import com.penait1.blockchain.chain.Main
import com.penait1.blockchain.db.LevelDB
import com.penait1.blockchain.miner.Miner
import com.penait1.blockchain.model.Blockchain
import java.util.*

fun main() {
    try {
        val blockchain = Blockchain.of(Main)

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
