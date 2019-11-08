Here is a list of common data structure implementations, along 
with my notes on them.

# Table of Contents:
- [Array](#array)
- [Vector](#vector)
- [Queue](#queue)

## Array
#### What is it?
An array is a fixed size linear data structure where data is stored in 
contiguous memory positions. As such, data is indexed, resulting in O(1)
access times. An array may not be resized - a new one must be created
and the old contents copied into it.

#### Average Complexities
 
| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(1)   | O(n)   | O(n)   | O(n)   |

**Notes on Complexity**
- Search is linear, all elements are traversed until matching element is
  found. Can be O(log n) if sorted and Binary Search is used.
- Insert and Delete are both O(1) if only considering setting the value,
  but climb to O(n) since the array must be copied into a larger/smaller
  array, depending on operation (ie: inserting an element at i means
  element >= i have to be transposed ahead by 1; deleting means elements
  are transposed back by 1).

#### Java Implementation
Native Java arrays.
```java
int[] array = new int[10];
int[][] jaggedArray = {{1, 2}, {1, 2, 3}};
```  

## Vector
#### What is it?
Also known as a mutable or dynamic array, it's capacity increases and
decreases according to the number of elements the array contains. This
makes it so that the array no longer has a fixed capacity, but also
means that it is periodically recreated (copied over to a new
larger/smaller array).

#### Implementation
On Insert/Delete, if size of elements in the array reached a certain
point, the array size should be increased/decreased. Rule of thumb:
double size if capacity is half used on insert; halve size if capacity
is 1/4 used on delete.

#### Average Complexities

| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(1)   | O(n)   | O(n)   | O(n)   |

**Notes on Complexity**
- Besides transposing the remaining elements, inserting/deleting an
  element might also trigger a resize.
  
#### Java Implementation
[ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
or [Vector](https://docs.oracle.com/javase/8/docs/api/java/util/Vector.html). 
They differ on synchronization and size growth.  
Vector is synchronized, ArrayList is not (only one thread may call
methods on Vector at once, while ArrayList allows concurrent access).
For single thread applications, use ArrayList. For multi thread
applications, ArrayList can also be synchronized:
```java
List synchronizedArrayList = Collections.synchronizedList(new ArrayList(...));
```
Both ArrayList and Vector are dynamically sized, but ArrayList increases
buffer size by 50% increments, while Vector does so by 100%.

## Queue
#### What is it?
Data structure where data is stored and retrieved in a FIFO (first in,
first out) approach. Elements are added progressively (i.e. to the back
of the queue), and dequeuing retrieves the element at the front of the
queue.  
Queues can be implemented through arrays or linked lists.

#### Implementation
A problem with array based queues is that after it fills up, new
elements can only be added after all elements are removed from it (since
new elements are added at the back, but dequeue is from the front). A
circular queue can circumvent this problem. Circular queues use pointers
to track the head and the tail of the queue, meaning elements can be
inserted anytime there's available space. The issue of a queue filling
up can also be avoided if the circular array is dynamically sized
(similar to a vector, as it increases/decreases in size as the queue
fills up/empties out).  
Linked list based queues do not face this optimization issue.

#### Average Complexities
| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(n)   | O(n)   | O(1)   | O(1)   |

**Notes on Complexity**
- Access and search are non standard operations in a queue. Both are
  O(n) if relying on queue operations. If implemented as operations of
  the underlying structure (either arrays ou lined lists), the
  complexity would match them as well.
- Insert is treated as a queue operation (adding an element). Delete is
  treated as a dequeue. Both are array/linked list manipulation through
  indexes, so O(1).
  
#### Java Implementation
[Queue](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html)
interface implemented through a linked list. By using a linked list, it
benefits from the optimization of circular queues.
```java
Queue queue = new LinkedList();
```