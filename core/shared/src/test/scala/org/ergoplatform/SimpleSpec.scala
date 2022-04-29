package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class SimpleSpec extends AnyPropSpec with Matchers {

  property("format") {
    Simple.formatTimes(Seq(1L)) shouldBe List("1970-01-01T00:00:00")
  }

}
