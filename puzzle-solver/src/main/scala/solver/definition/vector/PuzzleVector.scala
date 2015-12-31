package solver.definition.vector

class PuzzleVector(val solids: Seq[Int], val totalSize: Int) {

  def blanksCount = solids.length + 1

  lazy val blanksSum = totalSize - solids.sum

  lazy val permutations: Seq[Permutation] = PermutationGenerator.all(this)

  /* targetSum = blanksSum + 2 because first and last blank may actually have value of 0 while countPermutations assumes
   *   that blanks should have length of at least 1 */
  lazy val permutationsCount = permutations.size



  private def countPermutations(blanksCount: Int, targetSum: Int): Int = {
    (( 1 to targetSum ) map { i =>
      if (blanksCount > 1 && blanksCount - 1 < targetSum - i) {
        countPermutations(blanksCount - 1, targetSum - i)
      } else if (blanksCount - 1 == targetSum - i) {
        1
      } else {
        0
      }
    }).sum
  }


}
