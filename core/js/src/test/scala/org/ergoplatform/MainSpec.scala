package org.ergoplatform

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalatest.BeforeAndAfterEach
import org.scalatest.matchers.should.Matchers
import org.scalatest.propspec.AnyPropSpec

class MainSpec extends AnyPropSpec with Matchers with BeforeAndAfterEach {

  override protected def beforeEach(): Unit = {
    super.beforeEach()
    // Initialize App
    Main.setupUI()
  }

  override protected def afterEach(): Unit = {
    document.body.innerHTML = ""
    super.afterEach()
  }

  property("HelloWorld") {
    document.querySelectorAll("p").count(_.textContent == "Hello World") shouldBe 1
  }

  property("ButtonClick") {
    def messageCount =
      document.querySelectorAll("p").count(_.textContent == "You clicked the button!")

    val button = document.querySelector("button").asInstanceOf[dom.html.Button]
    assert(button != null && button.textContent == "Click me!")
    assert(messageCount == 0)

    for (c <- 1 to 5) {
      button.click()
      assert(messageCount == c)
    }
  }
}