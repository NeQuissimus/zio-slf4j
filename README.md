# zio-slf4j
Referentially transparent logging with slf4j

Don't forget to include an implementation of `slf4j-api` (such as logback) on your classpath.

```
libraryDependencies += "com.nequissimus" %% "zio-slf4j" % "0.3.0"
```

## Usage

For a simple usage example, see [sample.scala](src/test/scala/sample.scala).

### Scalaz

`zio-slf4j`'s dependency on Scalaz is entirely optional. It will only be used if already available, and not brought in transitively.

If Scalaz is availble on the application's classpath, methods using `Show` can be used.
Otherwise, log messages will need to be of type `String`.
