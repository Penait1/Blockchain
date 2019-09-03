package main.kotlin.nl.penait1.blockchain

import main.kotlin.nl.penait1.blockchain.model.Block
import main.kotlin.nl.penait1.blockchain.model.Blockchain

fun main() {
    val index = 0
    val data = Object()
    val previousHash = ByteArray(0)
    val nonce = "Random"

    val genesisBlock = Block.new(index, data, previousHash, nonce)


    val blockchain = Blockchain(ArrayList())
    blockchain.addBlock(genesisBlock)

    while(blockchain.getCurrentBlockHeight() < 5) {
        blockchain.mineBlock()
    }

    println(blockchain.isChainValid())

    blockchain.mineBlock()
    println(blockchain.isChainValid())

}
