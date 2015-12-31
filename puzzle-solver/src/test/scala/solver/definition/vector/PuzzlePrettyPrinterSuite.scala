package solver.definition.vector

import org.scalatest.FunSuite
import solver.PuzzlePrettyPrinter

class PuzzlePrettyPrinterSuite extends FunSuite {

  import PuzzlesFixture._

  test("should print trivial puzzle") {
    assert(new PuzzlePrettyPrinter(trivialPuzzleDefinition).print() ==
      List(
        " 1",
        "1 ").mkString("\n"))
  }

  test("should print square puzzle") {
    assert(new PuzzlePrettyPrinter(smallPuzzleDefinition).print() ==
      List(
        "  1   ",
        "  1323",
        " 1    ",
        " 3    ",
        " 3    ",
        "11    ",
        " 1    ").mkString("\n"))
  }
}
