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
    blake.blake2bUpdate(ctx, js.Array(bytes.map(_.toShort):_*))
    val hash = blake.blake2bFinal(ctx).toArray[Short]
    hash shouldNot be(null)
    hash.length shouldBe 32
    val hashBytes = hash.map(x => x.toByte)
    println(hashBytes.toSeq)
    hashBytes shouldNot be(null)
    hashBytes.length shouldBe 32
  }
}
