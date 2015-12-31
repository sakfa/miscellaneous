package solver.state

import org.scalatest._
import solver.definition.vector.{PuzzlesFixture, Permutation}

class BoardStateSuite extends FunSuite {
  import PuzzlesFixture._
  def testBoard = new BoardState(Array.fill(4,5)(None))

  test("undefined board should print dots") {
    assert(testBoard.toString == ".....\n.....\n.....\n.....")
  }

  test("defined bits should print") {
    assert(testBoard
      .setCellState(1,2,0)
      .setCellState(2,1,1)
      .toString == ".....\n..0..\n.1...\n.....")
  }

  test("fill permutation by row should fill by row") {
    val string =
      testBoard.fillRowWithPermutation(0, smallPuzzleDefinition.rows(0).permutations.head)
               .fillRowWithPermutation(3, smallPuzzleDefinition.rows(3).permutations.head).toString
    assert(string == "00101\n.....\n.....\n00001")
  }
}
