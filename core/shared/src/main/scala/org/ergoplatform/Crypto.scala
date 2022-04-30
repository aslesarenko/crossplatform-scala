package org.ergoplatform

object Crypto {
  def blake2b256(bytes: Array[Byte]): Array[Byte] = Platform.blake2b256(bytes)
}
