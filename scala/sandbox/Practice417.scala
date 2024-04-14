// Sort by the length of the string in ascending order
// input:  List("scala", "rust", "ada")
// output: List("ada", "rust", "scala")

def len(s: String): Int = s.length
// List("scala", "rust", "ada").sortBy(len)



// Sort by the number of 's' in the string in ascending order
// input:  List("rust", "ada")
// output: List("ada", "rust")

def countS(s: String): Int = s.length - s.replaceAll("s", "").length
// List("rust", "ada").sortBy(countS)

// Sort by the List[Int] in descending order
// input:  List(5, 1, 2, 4, 3)
// output: List(5, 4, 3, 2, 1)

def negative(i: Int): Int = -i
// List(5, 1, 2, 4, 3).sortBy(negative)

// Sort by the number of 's' in the string in descending order
// input:  List("rust", "ada")
// output: List("rustj", "ada")

def countNegativeS(s: String): Int = -countS(s)
// List("rust", "ada").sortBy(countNegativeS)
