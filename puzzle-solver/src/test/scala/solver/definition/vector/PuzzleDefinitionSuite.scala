package solver.definition.vector

import org.scalatest._
import solver.PuzzlePrettyPrinter

class PuzzleDefinitionSuite extends FunSuite {
  import PuzzlesFixture._

  test("should count number of combination by cols for trivial puzzle") {
    assert(trivialPuzzleDefinition.numberOfCombinationsByCols == BigInt(1))
  }
  test("should count number of combination by rows for trivial puzzle") {
    assert(trivialPuzzleDefinition.numberOfCombinationsByRows == BigInt(1))
  }

  test("should count number of combination by cols for small puzzle") {
    assert(smallPuzzleDefinition.numberOfCombinationsByCols == BigInt(216))
  }
  test("should count number of combination by rows for small puzzle") {
    assert(smallPuzzleDefinition.numberOfCombinationsByRows == BigInt(192))
  }

  test("should count number of combinations for big puzzles by cols") {
    assert(bigPuzzleDefinition.numberOfCombinationsByCols == BigInt("332842827815882186075685511681590310699003014282844569600000000"))
  }
  test("should count number of combinations for big puzzles by rows") {
    assert(bigPuzzleDefinition.numberOfCombinationsByRows == BigInt("2820269889245428176120092277769243740077117000912323215360000000"))
  }

  test("should transpose correctly") {
    val transposed = new PuzzlePrettyPrinter(smallPuzzleDefinition.transposed).print()

    assert(transposed ==
              List( "     1 ",
                    "  13311",
                    "11     ",
                    " 3     ",
                    " 2     ",
                    " 3     ").mkString("\n"))
  }
}