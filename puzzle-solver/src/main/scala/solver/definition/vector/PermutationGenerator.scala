package solver.definition.vector



object PermutationGenerator {
  def first(puzzleVector: PuzzleVector): Permutation = {
    val numberOfSolids = puzzleVector.solids.size
    val blanks = Array.fill(numberOfSolids + 1)(1)
    blanks(0) = 0
    blanks(blanks.length - 1) = puzzleVector.blanksSum - numberOfSolids + 1
    new Permutation(puzzleVector, blanks)
  }

  def all(puzzleVector: PuzzleVector): Seq[Permutation] = {
    var result = List(first(puzzleVector))
    while (result.head.next != None) {
      result = result.head.next.orNull :: result
    }

    result.toSeq
  }
}
