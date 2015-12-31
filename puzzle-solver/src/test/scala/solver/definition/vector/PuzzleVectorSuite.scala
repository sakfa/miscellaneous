package solver.definition.vector

import org.scalatest.FunSuite

class PuzzleVectorSuite extends FunSuite
{
  val vector = new PuzzleVector(Seq(1,2,1), 7)
  val vectorWideSparse = new PuzzleVector(Seq(1,1,3), 25)
  val vectorWideDense = new PuzzleVector(Seq(1,3,1,1,1,3,1,1,4), 25)

  test("count blanks") {
    assert(vector.blanksCount == 4)
  }

  test("count sum of blanks lengths") {
    assert(vector.blanksSum == 3)
  }

  test("count number of permutations (easy)") {
    assert(vector.permutationsCount == 4)
  }

  test("count number of permutations (sparse)") {
    assert(vectorWideSparse.permutationsCount == 1330)
  }

  test("count number of permutations (dense)") {
    assert(vectorWideDense.permutationsCount == 10)
  }
}
