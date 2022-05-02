package org.ergoplatform

object Crypto {
  def blake2b256(bytes: Array[Byte]): Array[Byte] = Platform.blake2b256(bytes)
  def sha256(bytes: Array[Byte]): Array[Byte] = Platform.sha256(bytes)
  def bytesToHex(bytes: Array[Byte]): String = Platform.bytesToHex(bytes)
  def hexToBytes(hex: String): Array[Byte] = Platform.hexToBytes(hex)
}
