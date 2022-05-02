package org.ergoplatform

import scala.collection.immutable.ArraySeq
import scalajs.js

object Platform{
  def format(ts: Long) = {
    new js.Date(ts).toISOString()
  }

  def bytesToJsShorts(bytes: Array[Byte]): js.Array[Short] = {
    js.Array(ArraySeq.unsafeWrapArray(bytes.map(x => (x & 0xFF).toShort)):_*)
  }

  def jsShortsToBytes(jsShorts: js.Array[Short]): Array[Byte] = {
    jsShorts.toArray[Short].map(x => x.toByte)
  }

  def bytesToHex(bytes: Array[Byte]): String = {
    val jsShorts = bytesToJsShorts(bytes)
    Hash.utils.toHex(jsShorts)
  }

  def hexToBytes(hex: String): Array[Byte] = {
    val hashShorts = Hash.utils.toArray(hex, "hex")
    jsShortsToBytes(hashShorts)
  }

  def blake2b256(bytes: Array[Byte]): Array[Byte] = {
    val ctx = blake.blake2bInit(32, null)
    val jsShorts = bytesToJsShorts(bytes)
    blake.blake2bUpdate(ctx, jsShorts)
    val hash = blake.blake2bFinal(ctx)
    jsShortsToBytes(hash)
  }

  def sha256(bytes: Array[Byte]): Array[Byte] = {
    val hex = bytesToHex(bytes)
    val hashHex = Hash.sha256().update(hex, "hex").digest("hex")
    hexToBytes(hashHex)
  }
}