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