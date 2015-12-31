package solver.definition

import org.scalatest._
import solver.{PuzzlePrettyPrinter, Solver}
import solver.definition.vector.PuzzlesFixture

class SolverSuite extends FunSuite {
  import PuzzlesFixture._

  val solver = new Solver(smallPuzzleDefinition)

  test("solve first solution") {
    println(new PuzzlePrettyPrinter(smallPuzzleDefinition).print(solver.solve))
  }

}
