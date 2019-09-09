package com.penait1.blockchain.node

import com.penait1.blockchain.node.db.LevelDB
import com.penait1.blockchain.node.model.Blockchain

fun main() {
    try {
        Blockchain.main()
    } finally {
        LevelDB.db.close()
    }
}
