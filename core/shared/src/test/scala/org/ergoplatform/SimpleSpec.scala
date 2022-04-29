package org.ergoplatform

import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class SimpleSpec extends AnyPropSpec with Matchers {

  property("format") {
    val t1 = System.currentTimeMillis()
    val t2 = System.currentTimeMillis()
    val times = List(1651258792479L, 1651258792479L)
    Simple.formatTimes(times) shouldBe List("2022-04-29T18:59:52", "2022-04-29T18:59:52")
  }

}
