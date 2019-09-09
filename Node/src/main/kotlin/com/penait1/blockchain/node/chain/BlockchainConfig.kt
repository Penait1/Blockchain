package com.penait1.blockchain.node.chain

import com.penait1.blockchain.node.model.Block

interface BlockchainConfig {
    val blockTime: Long
    val genesisBlock: Block
}
