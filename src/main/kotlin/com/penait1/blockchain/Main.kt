package com.penait1.blockchain

import com.penait1.blockchain.chain.Main
import com.penait1.blockchain.miner.Miner
import com.penait1.blockchain.model.Blockchain

fun main() {
    val blockchain = Blockchain.of(Main.genesisBlock)

    val miner = Miner(blockchain)

    while (blockchain.blockHeight() < 5) {
        miner.mine()
    }
}
