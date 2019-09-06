package com.penait1.blockchain.model

class Blockchain(private val blocks: ArrayList<Block>, var currentDifficulty: Int = 1) {
    fun getCurrentBlockHeight() = this.blocks.size

    fun addBlock(block: Block) {
        this.blocks.add(block)

        if (this.blocks.size > 2016 && this.blocks.size % 2016 == 0) {
            this.currentDifficulty = this.currentDifficulty * 2
            println("Highering difficulty")
        }
    }

    fun isChainValid(): Boolean {
        this.blocks.subList(1, this.blocks.size).forEachIndexed { index, block ->
            // Not (index - 1) since we already did the -1 by sublisting
            var previousBlock = this.blocks.get(index)

            if (!Block.validate(block)) return false
            if (Block.calculateHashFromBlock(previousBlock) != block.previousHash) return false
        }
        return true
    }

    fun mineBlock() {
        var nonce = 1
        var newBlock =
            Block.new(this.blocks.size, Object(), blocks.last().previousHash, nonce.toString())
        while (!newBlock.currentHash.("0".repeat(this.currentDifficulty))) {
            nonce++;
            newBlock = Block.new(
                this.blocks.size,
                Object(),
                blocks.last().currentHash,
                nonce.toString()
            )
        }

        println("Block found: " + newBlock.currentHash)
        this.addBlock(newBlock)
    }

}
