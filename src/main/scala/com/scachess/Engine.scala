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
import com.scachess.Engine.board

import scala.annotation.tailrec
import scala.util.Random

object Engine extends App {
  private val board = Board.init()

  @tailrec
  private def playTestGame(board: Board, move: Int): Unit = {
    val tile     = pickRandomTile(board)
    val newBoard = moveRandomly(board, tile)
    println(s"${FenHelper.toFen(newBoard)} w KQkq - 0 1")
    if (move > 0) playTestGame(newBoard, move - 1)
  }

  private def pickRandomTile(board: Board) = {
    val tilesWithPieces: Set[Tile] = board.position.tiles.filter(_.piece.nonEmpty)
    val rnd = new Random()
    tilesWithPieces.toVector(rnd.nextInt(tilesWithPieces.size))
  }

  private def moveRandomly(board: Board, tile: Tile): Board = {
    val rnd = new Random()
    tile.piece.fold(board)(piece => {
      println(s"piece is $piece")
      val moves = piece.getMoves(tile.pos)
      println(s"moves are $moves")
      val pick = moves.toVector(rnd.nextInt(moves.size))
      board.move(tile, board(pick.file, pick.rank))
    })
  }

  playTestGame(board, 10)

}
