package solver.state

import solver.definition.vector.Permutation

class BoardState(val states: Array[Array[Option[Byte]]]) {
  def sliceRow(row: Int) = states(row)

  def sliceCol(col: Int) = {
    for (row <- 0 until states.length) yield {
      states(row)(col)
    }.toArray
  }

  def setCellState(row: Int, col: Int, state: Byte): BoardState = {
    states(row)(col) = Some(state)
    this
  }

  def fillRowWithPermutation(row: Int, permutation: Permutation): BoardState = {
    permutation.byteNotation.zipWithIndex.foreach {
      case (byte, index) => setCellState(row,index,byte)
    }
    this
  }

  def fillColumnWithPermutation(column: Int, permutation: Permutation): BoardState = {
    this
  }

  override def toString = {
    states.map { row =>
      row.map { cell => {
        cell.fold(".")(b => b.toString)
      }}.mkString("")
    }.mkString("\n")
  }

  override def clone: BoardState = new BoardState(states.clone())
}

object BoardState {

}