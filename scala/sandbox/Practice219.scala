// Java
// class TipCalculator {
//   public static int getTipPercentage(List<String> names) {
//     if(names.size() > 5) {
//       return 20;
//     } else if(names.size() > 0) {
//       return 10;
//     } else return 0;
//   }
// }

object TipCalculator {
  def getTipPercentage(names: List[String]): Int = {
    if (names.size > 5) 20
    else if (names.size > 0) 10
    else 0
  }
}

// sbt console

// scala> TipCalculator.getTipPercentage(List.empty)
// val res0: Int = 0

// scala> val smallGroup = List("Alice", "Bob", "Charlie")
// val smallGroup: List[String] = List(Alice, Bob, Charlie)

// scala> TipCalculator.getTipPercentage(smallGroup)
// val res1: Int = 10

// scala> val largeGroup = List("Alice", "Bob", "Charlie", "Daniel", "Emily", "Frank")
// val largeGroup: List[String] = List(Alice, Bob, Charlie, Daniel, Emily, Frank)

// scala> TipCalculator.getTipPercentage(largeGroup)
// val res2: Int = 20
