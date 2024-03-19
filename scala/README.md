# Scala

https://github.com/miciek/grokkingfp-examples

## REPL

```
# sbt console
```

cording

```
scala> def increment(x: Int): Int = {
     |   x + 1
     | }
increment: (x: Int)Int

scala> increment(6)
res1: Int = 7
```

quit

```
scala> :q
```

## Compile

https://scala-text.github.io/scala_text/sbt-compile-execute.html

```
# cd sandbox
# sbt
```

run

```
sbt:sandbox> run
...
Hello, World!
```

exit

```
sbt:sandbox> exit
```

use from REPL

```
scala> val u = new User("hoge", 13)
val u: User = User@34355e3b
                                                                                                                                                                             
scala> User.printUser(u)
hoge 13
```
