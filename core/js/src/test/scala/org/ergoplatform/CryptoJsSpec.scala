package org.ergoplatform

import org.scalatest.propspec.AnyPropSpec
import org.scalatest.matchers.should.Matchers

import scala.scalajs.js

class CryptoJsSpec extends AnyPropSpec with Matchers {

  property("elliptic") {
    val c = EC.curve
    c shouldNot be(null)
  }

  property("blake2b") {
    blake shouldNot be(null)
    val bytes = Array[Byte](1, 2, 3)
    val ctx = blake.blake2bInit(32, null)
    ctx shouldNot be(null)
    blake.blake2bUpdate(ctx, Platform.bytesToJsShorts(bytes))
    val hash = blake.blake2bFinal(ctx).toArray[Short]
    hash shouldNot be(null)
    hash.length shouldBe 32
    val hashBytes = hash.map(x => x.toByte)
    println(hashBytes.toSeq)
    hashBytes shouldNot be(null)
    hashBytes.length shouldBe 32
  }

  property("toHex/fromHex") {
    val xs = Array[Short](1, 2, 3)
    val jsxs = js.Array(xs.toSeq:_*)
    val hex = Hash.utils.toHex(jsxs)
    hex shouldBe "010203"
    val jsys = Hash.utils.toArray(hex, "hex")
    val ys = jsys.toArray[Short].map(_.toByte)
    ys shouldBe xs
  }

  property("sha256") {
    val sha256 = Hash.sha256()
    sha256 shouldNot be(null)
    sha256.blockSize shouldBe 512
    sha256.outSize shouldBe 256

    val hashHex = sha256.update("deadbeef", "hex").digest("hex")
    hashHex shouldBe "5f78c33274e43fa9de5659265c1d917e25c03722dcb0b8d27db8d5feaa813953"
  }


}
