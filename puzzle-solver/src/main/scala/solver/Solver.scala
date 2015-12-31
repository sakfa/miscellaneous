package solver

import solver.definition.PuzzleDefinition

class Solver(puzzle: PuzzleDefinition) {
  def solve: PuzzleSolution = {
    val firstSolution = new PuzzleSolution(puzzle, Array.fill(puzzle.rows.size)(0))

    firstSolution
  }
}

class PuzzleBoard()

class PuzzleSolution(puzzle: PuzzleDefinition, val permutationIndexes: Seq[Int]) {

}