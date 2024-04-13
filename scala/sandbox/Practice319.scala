def firstTwo(list: List[String]): List[String] = {
  list.slice(0, 2)
}
// firstTwo(List("a", "b", "c")) == List("a", "b")

def lastTwo(list: List[String]): List[String] = {
  list.slice(list.size - 2, list.size)
}
// lastTwo(List("a", "b", "c")) == List("b", "c")

def movedFirstTwoToTheEnd(list: List[String]): List[String] = {
  var head = list.slice(0, 2)
  var tail = list.slice(2, list.size)
  tail.appendedAll(head)
}
// movedFirstTwoToTheEnd(List("a", "b", "c")) == List("c", "a", "b")

def insertBeforeLast(list: List[String], elem: String): List[String] = {
  var headList = list.slice(0, list.size - 1)
  var tailElem = list.last
  headList.appended(elem).appended(tailElem)
}
// insertBeforeLast(List("a", "b"), "c") == List("a", "c", "b")
