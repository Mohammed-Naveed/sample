object Brackets extends App {

  // 100%
  def solution(S: String): Int = {

    def go(i: Int, list: List[Char]): Int = {
      if (i >= S.length) {
        if (list.nonEmpty) 0 else 1
      } else {
        S(i) match {
          case '{' | '[' | '(' => go(i + 1, S(i) :: list)
          case '}' =>
            list match {
              case Nil                         => 0
              case head :: tail if head == '{' => go(i + 1, tail)
              case _                           => go(i + 1, list)
            }

          case ']' =>
            list match {
              case Nil                         => 0
              case head :: tail if head == '[' => go(i + 1, tail)
              case _                           => go(i + 1, list)
            }

          case ')' =>
            list match {
              case Nil                         => 0
              case head :: tail if head == '(' => go(i + 1, tail)
              case _                           => go(i + 1, list)
            }

        }
      }
    }
    go(0, List.empty)
  }

  // 62% (correct but bad performance)
  def solutionR(S: String): Int = {
    val fail = List('a')
    def examine(i: Int, L: List[Char]): List[Char] = {
      def check(c: Char): List[Char] = {
        if (L.isEmpty) fail
        else if (L.head == c) examine(i + 1, L.tail)
        else fail
      }

      if (i == S.length) L
      else
        S(i) match {
          case '{' | '[' | '(' => examine(i + 1, S(i) :: L)
          case '}'             => check('{')
          case ']'             => check('[')
          case ')'             => check('(')
        }
    }
    if (examine(0, List[Char]()).nonEmpty) 0 else 1
  }

  val goodString = "{[()()]}"
  val badString = "([)()]"
  val badderString = ")[][()]}rerer090][][][][][][][][][]["
  println(solution(goodString))
  println(solution(badString))
  println(solution(badderString))
  /*

  A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:

    S is empty;
  S has the form '(U)' or "[U]" or "{U}" where U is a properly nested string;
  S has the form "VW" where V and W are properly nested strings.
    For example, the string "{[()()]}" is properly nested but "([)()]" is not.

  Write a function:

  object Solution { def solution(S: String): Int }

  that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.

    For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.

    Assume that:

    N is an integer within the range [0..200,000];
  string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
    Complexity:

    expected worst-case time complexity is O(N);
  expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 */
}
