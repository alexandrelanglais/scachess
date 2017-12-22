//  Copyright (c) 2017 Alexandre Langlais
//
//  Permission is hereby granted, free of charge, to any person obtaining a copy
//  of this software and associated documentation files (the "Software"), to deal
//  in the Software without restriction, including without limitation the rights
//  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
//  copies of the Software, and to permit persons to whom the Software is
//  furnished to do so, subject to the following conditions:
//
//  The above copyright notice and this permission notice shall be included in all
//  copies or substantial portions of the Software.
//
//  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
//  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
//  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
//  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
//  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
//  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
//  SOFTWARE.

package com.scachess

import enumeratum._
import enumeratum.values.IntEnum
import enumeratum.values.IntEnumEntry

sealed abstract class Coordinates(val value: Int, val name: String) extends IntEnumEntry

object Coordinates {
  val FILE_A: Int = 0
  val FILE_B: Int = 1
  val FILE_C: Int = 2
  val FILE_D: Int = 3
  val FILE_E: Int = 4
  val FILE_F: Int = 5
  val FILE_G: Int = 6
  val FILE_H: Int = 7

  val RANK_8: Int = 7
  val RANK_7: Int = 6
  val RANK_6: Int = 5
  val RANK_5: Int = 4
  val RANK_4: Int = 3
  val RANK_3: Int = 2
  val RANK_2: Int = 1
  val RANK_1: Int = 0
}

object CoordinatesX extends IntEnum[Coordinates] {
  val values = findValues

  case object FILE_A extends Coordinates(value = 0, name = "A")
  case object FILE_B extends Coordinates(value = 1, name = "B")
  case object FILE_C extends Coordinates(value = 2, name = "C")
  case object FILE_D extends Coordinates(value = 3, name = "D")
  case object FILE_E extends Coordinates(value = 4, name = "E")
  case object FILE_F extends Coordinates(value = 5, name = "F")
  case object FILE_G extends Coordinates(value = 6, name = "G")
  case object FILE_H extends Coordinates(value = 7, name = "H")

}
object CoordinatesY extends IntEnum[Coordinates] {
  val values = findValues

  case object RANK_8 extends Coordinates(value = 7, name = "8")
  case object RANK_7 extends Coordinates(value = 6, name = "7")
  case object RANK_6 extends Coordinates(value = 5, name = "6")
  case object RANK_5 extends Coordinates(value = 4, name = "5")
  case object RANK_4 extends Coordinates(value = 3, name = "4")
  case object RANK_3 extends Coordinates(value = 2, name = "3")
  case object RANK_2 extends Coordinates(value = 1, name = "2")
  case object RANK_1 extends Coordinates(value = 0, name = "1")

}
