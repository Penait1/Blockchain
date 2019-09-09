package com.penait1.blockchain.node.chain

import com.penait1.blockchain.node.model.Block
import java.util.concurrent.TimeUnit

object Main : BlockchainConfig {
    //TODO set timestamp
    override val genesisBlock = Block.of(ByteArray(0), Any(), "Calvin-Jessie-Erik", System.currentTimeMillis())
    override val blockTime = TimeUnit.SECONDS.toMillis(10L)
}
