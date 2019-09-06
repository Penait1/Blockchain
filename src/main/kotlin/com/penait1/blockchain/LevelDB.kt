package com.penait1.blockchain

import java.io.File
import org.iq80.leveldb.DB
import org.iq80.leveldb.Options
import org.iq80.leveldb.impl.Iq80DBFactory.factory

object LevelDB {
    val db: DB

    init {
        val options = Options()
        options.createIfMissing(true)
        this.db = factory.open(File("leveldb"), options)
    }
}
