package org.ergoplatform

import scorex.crypto.hash

import java.text.SimpleDateFormat
import java.util.TimeZone

object Platform {

  def format(ts: Long) = {
    val fmt = new SimpleDateFormat(
      "yyyy-MM-dd'T'HH:mm:ss.sss'Z'"
    )
    fmt.setTimeZone(TimeZone.getTimeZone("UTC"))
    fmt.format(new java.util.Date(ts))
  }

  def blake2b256(bytes: Array[Byte]): Array[Byte] = {
    hash.Blake2b256.hash(bytes)
  }
}