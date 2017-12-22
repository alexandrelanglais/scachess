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

import com.scachess.Coordinates._
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Succeeded

@SuppressWarnings(Array("org.wartremover.warts.Any", "org.wartremover.warts.Option2Iterable"))
class PieceMovesTest extends FlatSpec with Matchers {

  "A piece" should "move one tile" in {
    val board = Board.init()

    val a2 = board(FILE_A, RANK_2)
    val a3 = board(FILE_A, RANK_3)
    val a4 = board(FILE_A, RANK_4)
    val c3 = board(FILE_C, RANK_3)
    val b1 = board(FILE_B, RANK_1)
    val c1 = board(FILE_C, RANK_1)
    val c2 = board(FILE_C, RANK_2)

    for {
      pawn <- a2.piece
      knight <- b1.piece
      bishop <- c1.piece
      _ = assert(pawn.canMoveTo(a2.pos, a3.pos))
      _ = assert(knight.canMoveTo(b1.pos, c3.pos))
      _ = assert(bishop.canMoveTo(b1.pos, c2.pos))
      _ = assert(bishop.canMoveTo(b1.pos, a2.pos))
      _ = assert(!knight.canMoveTo(a2.pos, a3.pos))
      _ = assert(!bishop.canMoveTo(b1.pos, c3.pos))
      _ = assert(!pawn.canMoveTo(b1.pos, c2.pos))

    } yield Succeeded
  }

  "A knight" should "be able to move around" in {
    val board = Board.init()

    val a2 = board(FILE_A, RANK_2)
    val a3 = board(FILE_A, RANK_3)
    val a4 = board(FILE_A, RANK_4)
    val c3 = board(FILE_C, RANK_3)
    val b1 = board(FILE_B, RANK_1)
    val c1 = board(FILE_C, RANK_1)
    val c2 = board(FILE_C, RANK_2)

    for {
      knight <- b1.piece

      _ = assert(knight.canMoveTo(b1.pos, c3.pos))
      _ = assert(!knight.canMoveTo(a2.pos, a3.pos))
    } yield Succeeded
  }

  "A bishop" should "be able to move various tiles" in {
    val board = Board.init()

    val a1 = board(FILE_A, RANK_1)
    val a8 = board(FILE_A, RANK_8)
    val c1 = board(FILE_C, RANK_1)
    val d1 = board(FILE_D, RANK_1)
    val e1 = board(FILE_E, RANK_1)
    val h6 = board(FILE_H, RANK_6)
    val h5 = board(FILE_H, RANK_5)

    for {

      bishop <- c1.piece

      _ = println(s"moving from ${c1.pos} to ${h6.pos}")
      _ = assert(bishop.canMoveTo(c1.pos, h6.pos))
      _ = assert(!bishop.canMoveTo(c1.pos, h5.pos))

    } yield Succeeded
  }

  "A rook" should "be able to move various tiles" in {
    val board = Board.init()

    val a1 = board(FILE_A, RANK_1)
    val a8 = board(FILE_A, RANK_8)
    val c1 = board(FILE_C, RANK_1)
    val d1 = board(FILE_D, RANK_1)
    val e1 = board(FILE_E, RANK_1)
    val h6 = board(FILE_H, RANK_6)
    val h5 = board(FILE_H, RANK_5)

    for {
      rook <- a1.piece

      _ = assert(rook.canMoveTo(a1.pos, a8.pos))
      _ = assert(rook.canMoveTo(a8.pos, a1.pos))
    } yield Succeeded
  }
}
