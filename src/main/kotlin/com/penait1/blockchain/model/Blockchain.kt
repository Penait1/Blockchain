package com.penait1.blockchain.model

class Blockchain(
    private val blocks: HashMap<ByteArray, Block> = HashMap()
) {

    fun addBlock(block: Block): Boolean {
        this.blocks[block.previous]?.let {
            if (block.hash().toString().startsWith("0".repeat(difficulity()))) {
                this.blocks[block.hash()] = block
                return true
            }
        }
        return false
    }

    fun blockHeight() = this.blocks.size

    //TODO calculate difficulity
    fun difficulity() = 1
}
