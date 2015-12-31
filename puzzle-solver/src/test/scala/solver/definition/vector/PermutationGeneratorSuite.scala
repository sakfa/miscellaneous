package solver.definition.vector

import org.scalatest.FunSuite

class PermutationGeneratorSuite extends FunSuite {
  val testVector = new PuzzleVector(Array(1,2,1), 8)

  test("all") {
    assert(PermutationGenerator.all(testVector).map(_.xNotation) ==
      List(
        "x-xx-x--",
        "x-xx--x-",
        "x-xx---x",
        "x--xx-x-",
        "x--xx--x",
        "x---xx-x",
        "-x-xx-x-",
        "-x-xx--x",
        "-x--xx-x",
        "--x-xx-x"
      ).reverse
    )
  }
}
