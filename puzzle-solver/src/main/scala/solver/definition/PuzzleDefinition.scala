package solver.definition

import solver.PuzzlePrettyPrinter
import solver.definition.vector.PuzzleVector

class PuzzleDefinition (
  val boardWidth: Int,
  val boardHeight: Int,
  val rows: Seq[PuzzleVector],
  val cols: Seq[PuzzleVector]
) {

  lazy val numberOfCombinationsByCols = cols.map(i => BigInt(i.permutationsCount)).product

  lazy val numberOfCombinationsByRows = rows.map(i => BigInt(i.permutationsCount)).product

  lazy val prettyPrint: String = new PuzzlePrettyPrinter(this).print()

  def transposed: PuzzleDefinition = new PuzzleDefinition(boardHeight, boardWidth, cols, rows)
}