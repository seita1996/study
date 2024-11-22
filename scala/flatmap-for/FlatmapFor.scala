case class User(id: Int, name: String)
case class Post(id: Int, userId: Int, content: String)
case class Comment(id: Int, postId: Int, content: String)
case class CountResult(id: Int, name: String, postCount: Int, commentCount: Int)

object FlatmapFor {

  def getUsers: Either[String, List[User]] = Right(List(User(1, "Alice"), User(2, "Bob"), User(3, "Charlie")))

  def getPosts: Either[String, List[Post]] = Right(List(Post(11, 1, "Alice\'s post"), Post(12, 1, "Alice\'s another post"), Post(13, 2, "Bob\'s post")))

  def getComments: Either[String, List[Comment]] = Right(List(Comment(101, 11, "Alice\'s comment"), Comment(102, 12, "Alice\'s another comment"), Comment(103, 13, "Bob\'s comment"), Comment(104, 13, "Bob\'s another comment")))

  // Using flatMap nesting
  def aggregateDataFlatMap: Either[String, List[CountResult]] = {
    getUsers.flatMap { users =>
      getPosts.flatMap { posts =>
        getComments.map { comments =>
          users.map { user =>
            val userPosts = posts.filter(_.userId == user.id)
            val postCount = userPosts.length
            val commentCount = userPosts.foldLeft(0) { (acc, post) =>
              acc + comments.count(_.postId == post.id)
            }
            CountResult(user.id, user.name, postCount, commentCount)
          }
        }
      }
    }
  }

  // Using for-comprehension
  def aggregateDataFor: Either[String, List[CountResult]] = {
    for {
      users <- getUsers
      posts <- getPosts
      comments <- getComments
    } yield {
      users.map { user =>
        val userPosts = posts.filter(_.userId == user.id)
        val postCount = userPosts.length
        val commentCount = userPosts.foldLeft(0) { (acc, post) =>
          acc + comments.count(_.postId == post.id)
        }
        CountResult(user.id, user.name, postCount, commentCount)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    aggregateDataFlatMap match {
      case Right(data) => println(s"FlatMap Result: $data")
      case Left(error) => println(s"Failed with error: $error")
    }

    aggregateDataFor match {
      case Right(data) => println(s"For-Comprehension Result: $data")
      case Left(error) => println(s"Failed with error: $error")
    }

    assert(aggregateDataFlatMap == Right(List(
      CountResult(1, "Alice", 2, 2),
      CountResult(2, "Bob", 1, 2),
      CountResult(3, "Charlie", 0, 0)
    )))

    assert(aggregateDataFor == Right(List(
      CountResult(1, "Alice", 2, 2),
      CountResult(2, "Bob", 1, 2),
      CountResult(3, "Charlie", 0, 0)
    )))
  }
}
