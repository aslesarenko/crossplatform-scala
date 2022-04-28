package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class BarSpec extends AnyPropSpec with Matchers {

  property("42") {
    Bar.a shouldBe 42
  }

}
