import { fromThrowable, ok, Result, safeTry } from 'neverthrow'

type User = { id: number, name: string }
type Post = { id: number, userId: number, title: string }
type Comment = { id: number, postId: number, content: string }

const users =
  (): Result<User[], Error> => {
    return fromThrowable(
      () => ([
        { id: 1, name: 'Alice' },
        { id: 2, name: 'Bob' },
        { id: 3, name: 'Charlie' }
      ]),
      () => new Error('Failed to fetch users')
    )()
  }

const posts =
  (): Result<Post[], Error> => {
    return fromThrowable(
      () => ([
        { id: 11, userId: 1, title: 'Alice\'s post' },
        { id: 12, userId: 1, title: 'Alice\'s another post' },
        { id: 13, userId: 2, title: 'Bob\'s post' }
      ]),
      () => new Error('Failed to fetch posts')
    )()
  }

const comments =
  (): Result<Comment[], Error> => {
    return fromThrowable(
      () => ([
        { id: 101, postId: 11, content: 'Alice\'s comment' },
        { id: 102, postId: 12, content: 'Alice\'s another comment' },
        { id: 103, postId: 13, content: 'Bob\'s comment' },
        { id: 104, postId: 13, content: 'Bob\'s another comment' }
      ]),
      () => new Error('Failed to fetch comments')
    )()
  }

type UserPostCommentCount = {
  postCount: number,
  commentCount: number
} & User;

// ユーザーごとにpost数とcomment数を集計する（andThenを利用）
const userPostCommentCount =
  (): Result<UserPostCommentCount[], Error> => {
    return users().andThen(
      users => {
        return posts().andThen(
          posts => {
            return comments().map(
              comments => {
                return users.map(
                  user => {
                    const postCount = posts.filter(
                      post => post.userId === user.id
                    ).length
                    const commentCount = comments.filter(
                      comment => {
                        return posts.find(
                          post => post.userId === user.id && post.id === comment.postId
                        )
                      }
                    ).length
                    return { ...user, postCount, commentCount }
                  }
                )
              }
            )
          }
        )
      }
    )
  }

// ユーザーごとにpost数とcomment数を集計する（safeTryを利用）
const userPostCommentCountSafeTry =
  (): Result<UserPostCommentCount[], Error> => {
    return safeTry(function*() {
      const userList = yield* users()
      const postList = yield* posts()
      const commentList = yield* comments()
      return ok(userList.map(
        user => {
          const postCount = postList.filter(
            post => post.userId === user.id
          ).length
          const commentCount = commentList.filter(
            comment => {
              return postList.find(
                post => post.userId === user.id && post.id === comment.postId
              )
            }
          ).length
          return { ...user, postCount, commentCount }
        }
      ))}
    )
  }

// 実行
console.log(userPostCommentCount())
console.log(userPostCommentCountSafeTry())

// assert
const expectedUserPostCommentCount = [
  { id: 1, name: 'Alice', postCount: 2, commentCount: 2 },
  { id: 2, name: 'Bob', postCount: 1, commentCount: 2 },
  { id: 3, name: 'Charlie', postCount: 0, commentCount: 0 }
]

console.assert(
  JSON.stringify(
    userPostCommentCount()._unsafeUnwrap()
  ) === JSON.stringify(expectedUserPostCommentCount)
)
console.assert(
  JSON.stringify(
    userPostCommentCountSafeTry()._unsafeUnwrap()
  ) === JSON.stringify(expectedUserPostCommentCount)
)