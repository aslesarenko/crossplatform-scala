package org.ergoplatform

import scalajs.js

object Platform {
  def bytesToJsShorts(bytes: Array[Byte]): js.Array[Short] = {
    js.Array(bytes.map(x => (x & 0xFF).toShort): _*)
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

  def blake2b256_BC(bytes: Array[Byte]): Array[Byte] = {
    val bc = BouncycastleJs.bouncyCastle
    val digest = bc.createBlake2bDigest(32 * 8)
    val in = BouncycastleJs.createByteArrayFromData(js.Array(bytes:_*))
    digest.$doUpdate(in, 0, bytes.length)
    val res = BouncycastleJs.createByteArrayFromData(new js.Array[Byte](32))
    digest.$doFinal(res, 0)
    res.data.toArray
  }
}