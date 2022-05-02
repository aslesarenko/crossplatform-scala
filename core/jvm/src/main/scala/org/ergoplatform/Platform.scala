package org.ergoplatform

import scorex.crypto.hash
import scorex.util.encode.Base16

object Platform {

  def blake2b256(bytes: Array[Byte]): Array[Byte] = {
    hash.Blake2b256.hash(bytes)
  }

  def sha256(bytes: Array[Byte]): Array[Byte] = {
    hash.Sha256.hash(bytes)
  }

  def bytesToHex(bytes: Array[Byte]): String = {
    Base16.encode(bytes)
  }

  def hexToBytes(hex: String): Array[Byte] = {
    Base16.decode(hex).get
  }

}