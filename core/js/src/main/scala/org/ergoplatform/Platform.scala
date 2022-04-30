package org.ergoplatform

import scala.collection.immutable.ArraySeq
import scalajs.js

object Platform{
  def format(ts: Long) = {
    new js.Date(ts).toISOString()
  }

  def blake2b256(bytes: Array[Byte]): Array[Byte] = {
    val ctx = blake.blake2bInit(32, null)
    val jsBytes = js.Array(ArraySeq.unsafeWrapArray(bytes.map(x => (x & 0xFF).toShort)):_*)
    blake.blake2bUpdate(ctx, jsBytes)
    val hash = blake.blake2bFinal(ctx)
    hash.toArray[Short].map(x => x.toByte)
  }
}