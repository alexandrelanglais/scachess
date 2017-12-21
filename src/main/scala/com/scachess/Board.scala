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

sealed trait Color
case object ColorWhite extends Color
case object ColorBlack extends Color

sealed trait SideToMove
case object SideToMoveWhite extends SideToMove
case object SideToMoveBlack extends SideToMove

sealed trait PieceType

case object Pawn   extends PieceType
case object Rook   extends PieceType
case object Knight extends PieceType
case object Bishop extends PieceType
case object Queen  extends PieceType
case object King   extends PieceType

final case class CastlingAllowed(
    wks: Boolean,
    wqs: Boolean,
    bks: Boolean,
    bqs: Boolean
)

final case class EnPassant(
    tile: Option[Tile]
)

final case class Piece(
    fencode:   Char,
    color:     Color,
    pieceType: PieceType
)

final case class Tile(
    color: Color,
    rank:  Int,
    file:  Int,
    piece: Option[Piece]
) extends Ordered[Tile] {
  override def compare(that: Tile): Int =
    if (this.rank == that.rank) this.file - that.file
    else that.rank - this.rank
}

final case class ChessPosition(
    tiles: Set[Tile]
)

final case class Board(
    position:         ChessPosition,
    posHistory:       List[ChessPosition],
    sideToMove:       SideToMove,
    castlingsAllowed: CastlingAllowed,
    enPassant:        EnPassant,
    fiftyMovesRule:   Int
) {
  def apply(rank: Int, file: Int): Option[Tile] = position.tiles.filter(t => t.rank == rank && t.file == file).headOption

}

object Board {

  def init(): Board = {
    val position         = ChessPosition(FenHelper.fromFen(FenHelper.initialPos))
    val posHistory       = Nil
    val sideToMove       = SideToMoveWhite
    val castlingsAllowed = CastlingAllowed(true, true, true, true)
    val enPassant        = EnPassant(None)
    val fiftyMovesRule   = 0

    Board(position, posHistory, sideToMove, castlingsAllowed, enPassant, fiftyMovesRule)
  }

  def loadFen(fen: String): Board = Board.init().copy(position = ChessPosition(FenHelper.fromFen(fen)))
}
