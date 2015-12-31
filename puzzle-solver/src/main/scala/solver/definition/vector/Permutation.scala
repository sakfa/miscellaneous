package solver.definition.vector

class Permutation(puzzleVector: PuzzleVector, blanks: Array[Int]) {
  lazy val byteNotation: Seq[Byte] = {
    xNotation.map {
      case 'x' => 1.toByte
      case '-' => 0.toByte
    }.toSeq
  }

  lazy val xNotation = {
    ("-" * blanks(0)) +
      puzzleVector.solids.zipWithIndex.map { case (solid: Int, i: Int) =>
        ("x" * solid) + ("-" * blanks(i + 1))
      }.mkString
  }

  override def toString = xNotation

  def next: Option[Permutation] = {
    var i = blanks.length - 1
    while (i > 0) {
      if (canShrink(i)) {
        val newBlanks = blanks.clone()
        newBlanks(i - 1) += 1
        resetBlanksToTheRightOf(newBlanks, i)
        return Some(new Permutation(puzzleVector, newBlanks))
      }
      i -= 1
    }
    None
  }

  //returns true if blank at index i can be shrinked legally
  private def canShrink(i: Int) = blanks(i) > 1 || (i == blanks.length - 1 && blanks(i) == 1)

  //resets blanks
  private def resetBlanksToTheRightOf(blanks: Array[Int], i: Int) = {
    var j = i
    var toBalance = 0
    while (j < blanks.length) {
      toBalance += blanks(j) - 1
      blanks(j) = 1
      j += 1
    }
    blanks(blanks.length - 1) = toBalance
  }
}
