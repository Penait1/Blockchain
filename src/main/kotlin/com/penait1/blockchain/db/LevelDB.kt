package com.penait1.blockchain.db

import java.io.File
import org.iq80.leveldb.DB
import org.iq80.leveldb.Options
import org.iq80.leveldb.impl.Iq80DBFactory.factory

object LevelDB {
    val db: DB = factory.open(File("leveldb"), Options().apply { createIfMissing(true) })
}
