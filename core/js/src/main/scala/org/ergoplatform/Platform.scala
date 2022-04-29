package org.ergoplatform

import scalajs.js

object Platform{
  def format(ts: Long) = {
    new js.Date(ts).toISOString()
  }
}