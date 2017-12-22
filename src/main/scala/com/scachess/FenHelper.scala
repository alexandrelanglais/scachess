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

import scala.annotation.tailrec
import scala.collection.immutable.SortedSet

final case class FenHelper(tileSet: Set[Tile], nRank: Int, nFile: Int, inc: Int)

object FenHelper {
  val initialPos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

  def fromFen(fen: String): Set[Tile] = {
    @tailrec
    def go(subFen: String, index: Int, tiles: Set[Tile], rank: Int, file: Int): Set[Tile] = subFen match {
      case x if (x.length > 0 && x.charAt(0) == ' ') => tiles
      case x if (x.length > 0) => {
        val color: Color = if (index % 2 == 0) ColorWhite else ColorBlack

        val c = x.charAt(0)
        val fh =
          if (c.isDigit) {
            val l  = c.toString.toInt
            val ts = (0 until l).flatMap(x => Set(Tile(ColorWhite, Position(rank, file + x), None))).toSet
            FenHelper(ts, rank, file + l, l)
          } else {
            c match {
              case 'r' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, Rook)))), rank, file + 1, 1)
              case 'b' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, Bishop)))), rank, file + 1, 1)
              case 'n' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, Knight)))), rank, file + 1, 1)
              case 'q' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, Queen)))), rank, file + 1, 1)
              case 'k' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, King)))), rank, file + 1, 1)
              case 'p' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorBlack, Pawn)))), rank, file + 1, 1)
              case 'R' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, Rook)))), rank, file + 1, 1)
              case 'B' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, Bishop)))), rank, file + 1, 1)
              case 'N' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, Knight)))), rank, file + 1, 1)
              case 'Q' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, Queen)))), rank, file + 1, 1)
              case 'K' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, King)))), rank, file + 1, 1)
              case 'P' => FenHelper(Set(Tile(color, Position(rank, file), Some(Piece(c, ColorWhite, Pawn)))), rank, file + 1, 1)
              case '/' => FenHelper(Set(), rank - 1, 0, 0)
              case _   => FenHelper(Set(), rank - 1, 0, 0)
            }
          }

        go(x.substring(1), index + fh.inc, tiles ++ fh.tileSet, fh.nRank, fh.nFile)
      }
      case _ => tiles
    }

    go(fen, 0, Set[Tile](), 7, 0)
  }

  @SuppressWarnings(Array("org.wartremover.warts.Any", "org.wartremover.warts.MutableDataStructures", "org.wartremover.warts.Var"))
  def toFen(board: Board): String = {
    val fen   = new scala.collection.mutable.StringBuilder(70)
    var empty = 0

    for (rank <- 7 to 0 by -1) {
      empty = 0
      for (file <- 0 to 7) {
        board(file, rank).piece match {
          case None => empty = empty + 1
          case Some(piece) => {
            if (empty == 0) fen append piece.fencode.toString
            else {
              fen append (empty.toString + piece.fencode.toString)
              empty = 0
            }
          }
        }
      }
      if (empty > 0) fen append empty
      if (rank > 0) fen append '/'
    }
    fen.toString
  }
  //    tiles.mkString("").flatMap(t => t.piece.fencode)

}
