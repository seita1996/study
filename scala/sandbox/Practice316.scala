// Alonzo Church -> A. Church
// A. Church -> A. Church
// A Church -> A. Church

def abbreviate(name: String): String = {
  var n = name.split(' ')
  return n(0).head.toString() + ". " + n(1)
}
