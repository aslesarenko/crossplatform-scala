package org.ergoplatform

import com.raquo.laminar.api.L._
import org.scalajs.dom
import org.scalajs.dom.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@js.native
@JSImport("stylesheets/main.scss", JSImport.Namespace)
object Css extends js.Any

object Main {
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    parNode.innerHTML = s"<a href=>$text</a>"
    val _ = targetNode.appendChild(parNode)
  }

  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def setupUI(): Unit = {
    val button = document.createElement("button")
    button.textContent = "Click me!"
    button.addEventListener("click", { (_: dom.MouseEvent) =>
      addClickedMessage()
    })
    document.body.appendChild(button)

    appendPar(document.body, "Hello World")
  }

  def main(args: Array[String]): Unit = {
    document.addEventListener("DOMContentLoaded", { (_: dom.Event) =>
      setupUI()
    })

    // laminar
    val appContainer = dom.document.querySelector("#app")
    val diffBus = new EventBus[Unit]
    val view = div(
      h3("Bar.a"),
      button(Bar.a.toString, onClick.map(_ => appendPar(document.body, "Clicked!")) --> diffBus)
    )
    val _ = render(appContainer, view)

//    waitForLoad {
//      val appContainer = dom.document.querySelector("#app")
//      appContainer.innerHTML = ""
//      unmount()
//      val view = div(
//        h3("Bar.a"),
//        div(Bar.a.toString)
//      )
//      val rootNode = render(appContainer, view)
//      storeUnmount(rootNode)
//    }
  }
  def waitForLoad(f: => Any): Unit =
    if (dom.window.asInstanceOf[js.Dynamic].documentLoaded == null) {
      val _ = documentEvents.onDomContentLoaded.foreach { _ =>
        dom.window.asInstanceOf[js.Dynamic].documentLoaded = true
        val _ = f
      }(unsafeWindowOwner)
    }
    else {
      val _ = f
    }

  def unmount(): Unit =
    if (scala.scalajs.LinkingInfo.developmentMode) {
      Option(dom.window.asInstanceOf[js.Dynamic].__laminar_root_unmount)
          .collect {
            case x if !js.isUndefined(x) =>
              x.asInstanceOf[js.Function0[Unit]]
          }
          .foreach { _.apply() }
    }

  def storeUnmount(rootNode: RootNode): Unit = {
    val unmountFunction: js.Function0[Any] = () => {rootNode.unmount()}
    dom.window.asInstanceOf[js.Dynamic].__laminar_root_unmount = unmountFunction
  }

}