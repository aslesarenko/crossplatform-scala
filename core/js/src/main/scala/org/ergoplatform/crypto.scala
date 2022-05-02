package org.ergoplatform

import scala.annotation.nowarn
import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

@JSImport("elliptic", "ec")
@js.native
@nowarn
class EC(name: String) extends js.Object {
//  def n: BN = js.native
}

object EC {
  val curve = new EC("secp256k1")
}

@JSImport("blakejs", JSImport.Default)
@js.native
object blake extends js.Object {

  @js.native
  trait Context extends js.Object

  @nowarn
  def blake2bInit(outputLength: Int, key: js.Any): Context = js.native

  @nowarn
  def blake2bUpdate(ctx: Context, bytes: js.Array[Short]): js.Any = js.native

  @nowarn
  def blake2bFinal(ctx: Context): js.Array[Short] = js.native
}

@JSImport("hash.js", JSImport.Default)
@js.native
object Hash extends js.Object {
  def sha256(): Sha256 = js.native
  val utils: Utils = js.native
}

@js.native
trait Utils extends js.Object {
  @nowarn
  def toArray(msg: String, enc: String): js.Array[Short] = js.native
  @nowarn
  def toHex(msg: js.Array[Short]): String = js.native
}

@js.native
trait MessageDigest[T] extends js.Object {
  val blockSize: Int = js.native
  val outSize: Int = js.native
}

@js.native
trait Sha256 extends js.Object with MessageDigest[Sha256] {
  @nowarn
  def update(msg: String, enc: String): Sha256 = js.native
  @nowarn
  def digest(enc: String): String = js.native
}