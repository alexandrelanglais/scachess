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

import java.util.UUID

final case class Piece(
    fencode:   Char,
    color:     Color,
    pieceType: PieceType,
    id:        String = UUID.randomUUID().toString
) {

  def canMoveTo(from: Position, to: Position): Boolean = pieceType match {
    case Pawn   => PawnMove(from, color).directions().contains(to)
    case Rook   => RookMove(from).directions().contains(to)
    case Knight => KnightMove(from).directions().contains(to)
    case Bishop => BishopMove(from).directions().contains(to)
    case Queen  => QueenMove(from).directions().contains(to)
    case King   => KingMove(from).directions().contains(to)
  }

  def getMoves(from: Position): Set[Position] = pieceType match {
    case Pawn   => PawnMove(from, color).directions()
    case Rook   => RookMove(from).directions()
    case Knight => KnightMove(from).directions()
    case Bishop => BishopMove(from).directions()
    case Queen  => QueenMove(from).directions()
    case King   => KingMove(from).directions()
  }

  def piecesThreatening(board: Board): Set[Tile] = {
    val myTile = board.position.tiles
      .filter(tile => tile.piece == Some(this))
      .toList(0)

    board.position.tiles
      .filter(tile => tile.piece.nonEmpty)
      .filterNot(tile => tile.piece == Some(this))
      .filter(tile =>
        tile.piece.fold(false) { piece =>
          piece.color != this.color &&
          piece.canMoveTo(tile.pos, myTile.pos)
      })
  }

  def isThreatened(board: Board): Boolean = piecesThreatening(board).nonEmpty
}
