package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class CryptoSpec extends AnyPropSpec with Matchers {

  property("blake2b256") {
    val bytes = Array[Byte](1, 2, 3)
    val hash = Crypto.blake2b256(bytes)
    hash shouldBe Array[Byte](17, -64, -25, -101, 113, -61, -105, 108, -51, 12, 2, -47, 49, 14, 37, 22, -64, -114, -36, -99, -117, 111, 87, -52, -42, -128, -42, 58, 77, -114, 114, -38)
  }

}
