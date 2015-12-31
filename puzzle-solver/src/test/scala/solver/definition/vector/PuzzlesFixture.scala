package solver.definition.vector

import solver.definition.PuzzleDefinition
import solver.state.BoardState

object PuzzlesFixture {
  val trivialPuzzleDefinition = new PuzzleDefinition(1, 1, Array(new PuzzleVector(Array(1), 1)), Array(new PuzzleVector(Array(1), 1)))
  val smallPuzzleDefinition = new PuzzleDefinition(
    4,5,
    Array(new PuzzleVector(Array(1),4),new PuzzleVector(Array(3),4),new PuzzleVector(Array(3),4),new PuzzleVector(Array(1,1),4),new PuzzleVector(Array(1),4)),
    Array(new PuzzleVector(Array(1,1),5),new PuzzleVector(Array(3),5),new PuzzleVector(Array(2),5),new PuzzleVector(Array(3),5))
  )
  val bigPuzzleDefinition = new PuzzleDefinition(
    25,25,
    Array(new PuzzleVector(Array(7, 3, 1, 1, 7), 25), new PuzzleVector(Array(1, 1, 2, 2, 1, 1), 25), new PuzzleVector(Array(1, 3, 1, 3, 1, 1, 3, 1), 25), new PuzzleVector(Array(1, 3, 1, 1, 6, 1, 3, 1), 25), new PuzzleVector(Array(1, 3, 1, 5, 2, 1, 3, 1), 25), new PuzzleVector(Array(1, 1, 2, 1, 1), 25), new PuzzleVector(Array(7, 1, 1, 1, 1, 1, 7), 25), new PuzzleVector(Array(3, 3), 25), new PuzzleVector(Array(1, 2, 3, 1, 1, 3, 1, 1, 2), 25), new PuzzleVector(Array(1, 1, 3, 2, 1, 1), 25), new PuzzleVector(Array(4, 1, 4, 2, 1, 2), 25), new PuzzleVector(Array(1, 1, 1, 1, 1, 4, 1, 3), 25), new PuzzleVector(Array(2, 1, 1, 1, 2, 5), 25), new PuzzleVector(Array(3, 2, 2, 6, 3, 1), 25), new PuzzleVector(Array(1, 9, 1, 1, 2, 1), 25), new PuzzleVector(Array(2, 1, 2, 2, 3, 1), 25), new PuzzleVector(Array(3, 1, 1, 1, 1, 5, 1), 25), new PuzzleVector(Array(1, 2, 2, 5), 25), new PuzzleVector(Array(7, 1, 2, 1, 1, 1, 3), 25), new PuzzleVector(Array(1, 1, 2, 1, 2, 2, 1), 25), new PuzzleVector(Array(1, 3, 1, 4, 5, 1), 25), new PuzzleVector(Array(1, 3, 1, 3, 10, 2), 25), new PuzzleVector(Array(1, 3, 1, 1, 6, 6), 25), new PuzzleVector(Array(1, 1, 2, 1, 1, 2), 25), new PuzzleVector(Array(7, 2, 1, 2, 5), 25)),
    Array(new PuzzleVector(Array(7, 2, 1, 1, 7), 25), new PuzzleVector(Array(1, 1, 2, 2, 1, 1), 25), new PuzzleVector(Array(1, 3, 1, 3, 1, 3, 1, 3, 1), 25), new PuzzleVector(Array(1, 3, 1, 1, 5, 1, 3, 1), 25), new PuzzleVector(Array(1, 3, 1, 1, 4, 1, 3, 1), 25), new PuzzleVector(Array(1, 1, 1, 2, 1, 1), 25), new PuzzleVector(Array(7, 1, 1, 1, 1, 1, 7), 25), new PuzzleVector(Array(1, 1, 3), 25), new PuzzleVector(Array(2, 1, 2, 1, 8, 2, 1), 25), new PuzzleVector(Array(2, 2, 1, 2, 1, 1, 1, 2), 25), new PuzzleVector(Array(1, 7, 3, 2, 1), 25), new PuzzleVector(Array(1, 2, 3, 1, 1, 1, 1, 1), 25), new PuzzleVector(Array(4, 1, 1, 2, 6), 25), new PuzzleVector(Array(3, 3, 1, 1, 1, 3, 1), 25), new PuzzleVector(Array(1, 2, 5, 2, 2), 25), new PuzzleVector(Array(2, 2, 1, 1, 1, 1, 1, 2, 1), 25), new PuzzleVector(Array(1, 3, 3, 2, 1, 8, 1), 25), new PuzzleVector(Array(6, 2, 1), 25), new PuzzleVector(Array(7, 1, 4, 1, 1, 3), 25), new PuzzleVector(Array(1, 1, 1, 1, 4), 25), new PuzzleVector(Array(1, 3, 1, 3, 7, 1), 25), new PuzzleVector(Array(1, 3, 1, 1, 1, 2, 1, 1, 4), 25), new PuzzleVector(Array(1, 3, 1, 4, 3, 3), 25), new PuzzleVector(Array(1, 1, 2, 2, 2, 6, 1), 25), new PuzzleVector(Array(7, 1, 3, 2, 1, 1), 25))
  )
}
