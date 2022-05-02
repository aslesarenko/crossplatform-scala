package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class CryptoSpec extends AnyPropSpec with Matchers {
  import Crypto._

  property("blake2b256") {
    val bytes = Array[Byte](1, 2, 3)
    val hash = blake2b256(bytes)
    hash shouldBe Array[Byte](17, -64, -25, -101, 113, -61, -105, 108, -51, 12, 2, -47, 49, 14, 37, 22, -64, -114, -36, -99, -117, 111, 87, -52, -42, -128, -42, 58, 77, -114, 114, -38)
  }

  property("sha256") {
    val bytes = hexToBytes("deadbeef")
    val hex = sha256(bytes)
    val hashHex = bytesToHex(hex)
    hashHex shouldBe "5f78c33274e43fa9de5659265c1d917e25c03722dcb0b8d27db8d5feaa813953"
  }

  property("bytesToHex / hexToBytes") {
    val bytes = Array[Byte](1, 2, 3)
    val hex = bytesToHex(bytes)
    val expHex = "010203"
    hex shouldBe expHex

    val decodedBytes = hexToBytes(expHex)
    decodedBytes shouldBe bytes
  }

}
