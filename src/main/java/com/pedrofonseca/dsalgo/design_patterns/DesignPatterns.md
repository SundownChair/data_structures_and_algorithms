Here is a list of common design pattern implementations, along with my
notes on them.

# Table of Contents:
- [Singleton](#singleton)

## Singleton
#### What is it?
> The Singleton pattern ensures a class has only one instance and
> provides a global point of access to it.

A singleton has a private constructor to prevent other classes from
creating instances. It has a static object of its own type that is
accessed by a static method that returns the globals instance, creating
it only if one doesn't exist yet.

