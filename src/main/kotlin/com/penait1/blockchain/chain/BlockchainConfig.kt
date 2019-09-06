package com.penait1.blockchain.chain

import com.penait1.blockchain.model.Block

interface BlockchainConfig {
    val blockTime: Long
    val genesisBlock: Block
}
