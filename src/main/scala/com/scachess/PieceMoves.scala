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
import scala.collection.immutable

sealed trait PieceMoves {
  def directions(): Set[Position]
}

sealed trait PawnMoves extends PieceMoves {
  def moveForward(pos: Position): Position
}

final case class PawnMove(pos: Position, color: Color) extends PawnMoves {
  override def directions(): Set[Position] = Set(
    moveForward(pos),
    moveForward(pos).copy(pos.rank + 1),
    moveForward(pos).copy(pos.file + 1),
    moveForward(pos).copy(pos.file - 1)
  ).filter(pos => pos.rank >= 0 && pos.rank < 8 && pos.file >= 0 && pos.file < 8)

  override def moveForward(pos: Position): Position = color match {
    case ColorWhite => pos.copy(pos.rank + 1)
    case ColorBlack => pos.copy(pos.rank - 1)
  }
}

final case class KnightMove(pos: Position) extends PieceMoves {
  override def directions() = Set(
    Position(pos.rank + 1, pos.file + 2),
    Position(pos.rank + 2, pos.file + 1),
    Position(pos.rank - 1, pos.file - 2),
    Position(pos.rank - 2, pos.file - 1),
    Position(pos.rank - 1, pos.file + 2),
    Position(pos.rank - 2, pos.file + 1),
    Position(pos.rank + 1, pos.file - 2),
    Position(pos.rank + 2, pos.file - 1),
  ).filter(pos => pos.rank >= 0 && pos.rank < 8 && pos.file >= 0 && pos.file < 8)
}

final case class BishopMove(pos: Position) extends PieceMoves {
  override def directions(): Set[Position] = {
    val xx: immutable.Seq[Set[Position]] = for {
      x <- 1 to 8
    } yield
      Set(
        Position(pos.rank + x, pos.file + x),
        Position(pos.rank + x, pos.file - x),
        Position(pos.rank - x, pos.file + x),
        Position(pos.rank - x, pos.file - x),
      )
    xx.flatten.toSet.filter(pos => pos.rank >= 0 && pos.rank < 8 && pos.file >= 0 && pos.file < 8)
  }
}

final case class RookMove(pos: Position) extends PieceMoves {
  override def directions(): Set[Position] = {
    val xx: immutable.Seq[Set[Position]] = for {
      x <- pos.rank to 8 - pos.rank
    } yield
      Set(
        Position(pos.rank + x, pos.file),
        Position(pos.rank - x, pos.file),
        Position(pos.rank, pos.file + x),
        Position(pos.rank, pos.file - x),
      )

    xx.flatten.toSet.filter(pos => pos.rank >= 0 && pos.rank < 8 && pos.file >= 0 && pos.file < 8)
  }
}

final case class QueenMove(pos: Position) extends PieceMoves {
  override def directions() = RookMove(pos).directions() ++ BishopMove(pos).directions()
}

final case class KingMove(pos: Position) extends PieceMoves {
  override def directions() = Set(
    Position(pos.rank + 1, pos.file + 1),
    Position(pos.rank + 1, pos.file - 1),
    Position(pos.rank - 1, pos.file + 1),
    Position(pos.rank - 1, pos.file - 1),
    Position(pos.rank + 1, pos.file),
    Position(pos.rank - 1, pos.file),
    Position(pos.rank, pos.file + 1),
    Position(pos.rank, pos.file - 1)
  ).filter(pos => pos.rank >= 0 && pos.rank < 8 && pos.file >= 0 && pos.file < 8)

}
