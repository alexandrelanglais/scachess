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

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import scala.collection.immutable.SortedSet
import scala.collection.immutable.TreeSet

class FenHelperTest extends FlatSpec with Matchers {
  "fromFen" should "be able to place one piece" in {
    val tiles = FenHelper.fromFen("r")

    assert(tiles === Set(Tile(ColorWhite, 7, 0, Some(Piece('r', ColorBlack, Rook)))))
  }

  it should "be able to place the original board position" in {
    val tiles = FenHelper.fromFen(FenHelper.initialPos)
    val board = Board.init()

    val tree = SortedSet[Tile]() ++ board.position.tiles
    println(tree.mkString("\n"))
    //    assert(tiles === Set(Tile(ColorWhite, 7, 0, Some(Piece('r', ColorBlack, Rook)))))

    println(FenHelper.toFen(board))
    assert(FenHelper.toFen(board) === FenHelper.initialPos.split(" ")(0))
  }
  it should "be able to place various positions" in {
    val fen1 = "6k1/5rb1/6Qp/1N2P3/8/1P3pP1/P4K1P/4R3 w - -"
    val board = Board.loadFen(fen1)

    println(FenHelper.toFen(board))
    assert(FenHelper.toFen(board) === fen1.split(" ")(0))
  }
}
