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

class BoardTest extends FlatSpec with Matchers {

  "A Board" should "have an empty fen string when not initialized" in {
    val position         = ChessPosition(Set())
    val posHistory       = Nil
    val sideToMove       = SideToMoveWhite
    val castlingsAllowed = CastlingAllowed(true, true, true, true)
    val enPassant        = EnPassant(None)
    val fiftyMovesRule   = 0
    val board            = Board(position, posHistory, sideToMove, castlingsAllowed, enPassant, fiftyMovesRule)

    assert(FenHelper.toFen(board) === "")
  }

  it should "have an initial fen string when initialized" ignore {
    val board = Board.init()

    assert(FenHelper.toFen(board) === "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

  }
}
