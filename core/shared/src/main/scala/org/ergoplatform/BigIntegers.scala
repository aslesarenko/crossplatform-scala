package org.ergoplatform

import java.security.SecureRandom

object BigIntegers {

  @throws[IllegalArgumentException]
  def createRandom(bitLength: Int, random: SecureRandom) = {
    if (bitLength < 1) throw new IllegalArgumentException("bitLength must be at least 1")
    val nBytes = (bitLength + 7) / 8
    val rv = new Array[Byte](nBytes)
    random.nextBytes(rv)

    // strip off any excess bits in the MSB
    val xBits = 8 * nBytes - bitLength
    rv(0) = (rv(0) & 255 >>> xBits).toByte
    rv
  }

}
