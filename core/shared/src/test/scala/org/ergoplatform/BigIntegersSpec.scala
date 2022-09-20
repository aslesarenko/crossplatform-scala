package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

import java.security.SecureRandom

class BigIntegersSpec extends AnyPropSpec with Matchers {
  val secureRandom = new SecureRandom()

  property("createRandom") {
    val r = BigIntegers.createRandom(8, secureRandom)
    r.length shouldBe 1
  }
}
