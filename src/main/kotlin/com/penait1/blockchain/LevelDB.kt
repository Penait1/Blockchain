package com.penait1.blockchain

import java.io.File
import org.fusesource.leveldbjni.JniDBFactory.factory
import org.iq80.leveldb.DB
import org.iq80.leveldb.Options


object LevelDB {
    var db: DB

    init {
        val options = Options()
        options.createIfMissing(true)
        this.db = factory.open(File("level"), options)
    }
}
