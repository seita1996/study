def increment(x: Int): Int = {
  x + 1
}
// increment(0) == 1
// increment(-1) == 0
// increment(-6) == -5
// increment(Integer.MAX_VALUE - 1) == Integer.MAX_VALUE

def add(a: Int, b: Int): Int = {
  a + b
}
// add(0, 0) == 0
// add(1, -1) == 0
// add(-1, -1) == -2
// add (1, 1) == 2
// add(9 , 1) == 10

def wordScore(word: String): Int = {
  word.replaceAll("a", "").length
}
// wordScore("scala") == 3
// wordScore("haskell") == 6
// wordScore("a") == 0
// wordScore("ruby") == 4
// wordScore("") == 0

def getTipPercentage(names: List[String]): Int = {
  if (names.size > 5) 20
  else if (names.size > 0) 10
  else 0
}
// getTipPercentage(List("Alice", "Bob")) == 10
// getTipPercentage(List("Alice", "Bob", "John", "Danihel", "Ken", "Mai")) == 20
// getTipPercentage(List()) == 0

def getFirstCharacter(s: String): Char = {
  if (s.length > 0) s.charAt(0)
  else ' '
}
// getFirstCharacter("scala") == 's'
// getFirstCharacter("haskell") == 'h'
// getFirstCharacter("") == ' '
// getFirstCharacter(" hi ") == ' '
