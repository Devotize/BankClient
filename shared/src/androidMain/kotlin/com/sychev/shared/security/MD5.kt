package com.sychev.shared.security

import java.util.Formatter
import kotlin.math.abs
import kotlin.math.sin

actual class MD5 {

    actual fun encrypt(data: Any?): String {
        val message = data.toString().toByteArray()
        val paddedMessage = padMessage(message)
        val blocks = createBlocks(paddedMessage)
        val initialState = intArrayOf(INIT_A, INIT_B, INIT_C, INIT_D)
        val state = processBlocks(blocks, initialState)
        val md5 = convertToByteArray(state)
        return formatByteArray(md5)
    }

    private fun padMessage(message: ByteArray): ByteArray {
        val messageLenBytes = message.size
        val numBlocks = ((messageLenBytes + 8) ushr 6) + 1
        val totalLen = numBlocks shl 6
        val paddingBytes = ByteArray(totalLen - messageLenBytes)
        paddingBytes[0] = 0x80.toByte()
        var messageLenBits = (messageLenBytes.toLong() shl 3)
        for (i in 0 until 8) {
            paddingBytes[paddingBytes.size - 8 + i] = (messageLenBits and 0xFF).toByte()
            messageLenBits = messageLenBits ushr 8
        }
        return message + paddingBytes
    }

    private fun createBlocks(paddedMessage: ByteArray): List<LongArray> {
        val blocks = mutableListOf<LongArray>()
        for (i in 0 until paddedMessage.size step 64) {
            val block = LongArray(16)
            for (j in 0 until 16) {
                block[j] =
                    ((paddedMessage[i + (j * 4)].toInt() and 0xFF).toLong() shl 24) or
                            ((paddedMessage[i + (j * 4) + 1].toInt() and 0xFF).toLong() shl 16) or
                            ((paddedMessage[i + (j * 4) + 2].toInt() and 0xFF).toLong() shl 8) or
                            (paddedMessage[i + (j * 4) + 3].toInt() and 0xFF).toLong()
            }
            blocks.add(block)
        }
        return blocks
    }

    private fun processBlocks(blocks: List<LongArray>, initialState: IntArray): IntArray {
        var a = initialState[0]
        var b = initialState[1]
        var c = initialState[2]
        var d = initialState[3]
        for (block in blocks) {
            val originalA = a
            val originalB = b
            val originalC = c
            val originalD = d
            for (j in 0 until 64) {
                val div16 = j ushr 4
                var f = 0
                var bufferIndex = j
                when (div16) {
                    0 -> f = (b and c) or (b.inv() and d)
                    1 -> {
                        f = (b and d) or (c and d.inv())
                        bufferIndex = (bufferIndex * 5 + 1) and 0x0F
                    }

                    2 -> {
                        f = b xor c xor d
                        bufferIndex = (bufferIndex * 3 + 5) and 0x0F
                    }

                    3 -> {
                        f = c xor (b or d.inv())
                        bufferIndex = (bufferIndex * 7) and 0x0F
                    }
                }
                val temp =
                    b + (a + f + block[bufferIndex] + TABLE_T[j]).rotateLeft(SHIFT_AMTS[(div16 shl 2) or (j and 3)])
                        .toInt()
                a = d
                d = c
                c = b
                b = temp
            }
            a += originalA
            b += originalB
            c += originalC
            d += originalD
        }
        return intArrayOf(a, b, c, d)
    }

    private fun convertToByteArray(state: IntArray): ByteArray {
        val md5 = ByteArray(16)
        var count = 0
        for (i in 0 until 4) {
            val n =
                if (i == 0) state[0] else if (i == 1) state[1] else if (i == 2) state[2] else state[3]
            for (j in 0 until 4) {
                md5[count++] = (n and 0xFF).toByte()
                n ushr 8
            }
        }
        return md5
    }

    private fun formatByteArray(md5: ByteArray): String {
        val formatter = Formatter()
        md5.forEach { formatter.format("%02x", it) }
        return formatter.toString()
    }

    companion object {
        private const val INIT_A = 0x67452301
        private const val INIT_B = 0xEFCDAB89.toInt()
        private const val INIT_C = 0x98BADCFE.toInt()
        private const val INIT_D = 0x10325476
        private val SHIFT_AMTS = intArrayOf(
            7, 12, 17, 22, 5, 9, 14, 20, 4,
            11, 16, 23, 6, 10, 15, 21
        )
        private val TABLE_T = IntArray(64)

        init {
            for (i in 0 until 64)
                TABLE_T[i] = (1L shl 32 * abs(sin(i + 1.0)).toInt()).toInt()
        }
    }
}