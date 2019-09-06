package com.penait1.blockchain

import com.penait1.blockchain.chain.Main
import com.penait1.blockchain.miner.Miner
import com.penait1.blockchain.model.Blockchain

fun main() {
    try {
        LevelDB.db.put("test".toByteArray(), "test".toByteArray())
        println(String(LevelDB.db.get("test".toByteArray())))

        val blockchain = Blockchain()
        blockchain.addBlock(Main.genesisBlock)

        val miner = Miner(blockchain, Main.genesisBlock)

        while (blockchain.blockHeight() < 5) {
            miner.mine()
        }
    } finally {
        LevelDB.db.close()
    }
}
