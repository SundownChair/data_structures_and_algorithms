Here is a list of common design pattern implementations, along with my
notes on them.

# Table of Contents:
- [Singleton](#singleton)
- [Factory Method](#factory-method)

## Singleton
#### What is it?
> The Singleton pattern ensures a class has only one instance and
> provides a global point of access to it.

A singleton has a private constructor to prevent other classes from
creating instances. It has a static object of its own type that is
accessed by a static method that returns the globals instance, creating
it only if one doesn't exist yet.

## Factory Method
#### What is it?
> The Factory Method pattern defines an interface for creating an object
> but lets subclasses decide which class to instantiate. The Factory
> Method lets a class defer instantiation it uses to subclasses.

Used whenever a class cannot predict what subtype of object it will need
or when complex business logic exist when creating new objects
(centralizing it in a factory instead of having it spread out throughout
the code).  

A Factory will usually consist of an interface/abstract class and
implementations that handle the specific object creation. The Simple
Factory anti-pattern omits the interface and directly creates concrete
factories (seldom used).
