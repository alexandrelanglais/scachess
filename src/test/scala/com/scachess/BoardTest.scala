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

import java.util

import com.scachess.Coordinates._
import org.scalatest.FlatSpec
import org.scalatest.Matchers
import org.scalatest.Succeeded

import scala.collection.immutable.SortedSet

@SuppressWarnings(Array("org.wartremover.warts.Any", "org.wartremover.warts.Option2Iterable"))
class BoardTest extends FlatSpec with Matchers {

  "A Board" should "have an empty fen string when not initialized" ignore {
    val position         = ChessPosition(Set())
    val posHistory       = Nil
    val sideToMove       = SideToMoveWhite
    val castlingsAllowed = CastlingAllowed(true, true, true, true)
    val enPassant        = EnPassant(None)
    val fiftyMovesRule   = 0
    val board            = Board(position, posHistory, sideToMove, castlingsAllowed, enPassant, fiftyMovesRule)

    assert(FenHelper.toFen(board) === "///////")
  }

  it should "have an initial fen string when initialized" in {
    val board = Board.init()

    assert(FenHelper.toFen(board) === "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR")
  }

  it should "be able to retrieve piece at coords" in {
    val board = Board.init()

    for {
      piece <- board(FILE_A, RANK_1).piece
      _ = assert(piece.fencode == 'R')
      piece <- board(FILE_H, RANK_8).piece
      _ = assert(piece.fencode == 'r')
      piece <- board(FILE_H, RANK_7).piece
      _ = assert(piece.fencode == 'p')
    } yield Succeeded
  }

  it should "be able to detect threatened pieces" in {
    val pos   = "r1bqkbnr/ppp1pppp/2n5/3p4/3P4/5N2/PPP1PPPP/RNBQKB1R"
    val board = Board.loadFen(pos)

    for {
      piece <- board(FILE_D, RANK_4).piece
      _ = assert(piece.isThreatened(board))

      piece <- board(FILE_C, RANK_6).piece
      _ = assert(!piece.isThreatened(board))
    } yield Succeeded

  }

  it should "be able to retrieve all threatened pieces" in {
    val pos     = "r1bqkb1r/p1p1ppp1/1pn2n1p/1B1p1Q2/3P4/4PN2/PPP2PPP/RNB1K2R"
    val board   = Board.loadFen(pos)
    val threats = board.allThreatenedPieces()
    val sorted  = threats.map(_.pos.toString)

    println(sorted)
    assert(sorted.contains("C6"))
    assert(sorted.contains("F5"))
    assert(sorted.contains("C8"))
    assert(sorted.contains("C8"))
  }

  it should "be able to move a piece" in {
    val board = Board.init()

    val tileFrom = board(FILE_E, RANK_2)
    val tileTo   = board(FILE_E, RANK_4)
    val nb       = board.move(tileFrom, tileTo)
    assert(FenHelper.toFen(nb) == "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR")

  }
}
