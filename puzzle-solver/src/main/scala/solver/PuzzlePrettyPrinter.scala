package solver

import solver.definition.PuzzleDefinition

class PuzzlePrettyPrinter(puzzle: PuzzleDefinition)
{

  lazy val maxHintsInRows = puzzle.rows.map(_.solids.size).max
  lazy val maxHintsInCols = puzzle.cols.map(_.solids.size).max

  def print(): String = print(None)

  def print(solution: PuzzleSolution): String = print(Some(solution))

  def print(solution: Option[PuzzleSolution]) = {
    val builder = new StringBuilder()

    printColsHints(builder)
    printRowHints(builder, solution)

    builder.toString()
  }

  private def printColsHints(builder: StringBuilder): Unit = {
    for (row <- 0 until maxHintsInCols) {
      builder.append(" " * maxHintsInRows)
      for (col <- 0 until puzzle.boardWidth) {
        val solids = puzzle.cols(col).solids
        if (maxHintsInCols - row > solids.length) {
          builder.append(" ")
        } else {
          builder.append(solids(solids.length - (maxHintsInCols - row)))
        }
      }
      builder.append("\n")
    }
  }

  def printRowHints(builder: StringBuilder, solution: Option[PuzzleSolution]): Unit = {
    for (row <- 0 until puzzle.boardHeight) {
      for (col <- 0 until maxHintsInRows) {
        val solids = puzzle.rows(row).solids
        if (maxHintsInRows - col > solids.length) {
          builder.append(" ")
        } else {
          builder.append(solids(solids.length - (maxHintsInRows - col)))
        }
      }

      builder.append(
        solution.fold(
          " " * puzzle.boardWidth
        )(s => {
          puzzle.rows(row).permutations(s.permutationIndexes(row)).xNotation
        })
      )

      if (row < puzzle.boardHeight - 1) {
        builder.append("\n")
      }
    }
  }
}
