Here is a list of common data structure implementations, along 
with my notes on them.

# Table of Contents:
- [Array](#array)
- [Vector](#vector)
- [Queue](#queue)
- [Stack](#stack)
- [Deque](#deque)
- [Heap](#heap)

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
  the underlying structure (either arrays ou linked lists), the
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

## Stack
#### What is it?
Data structure where data is stored according to a LIFO (last in, first
out) approach. Elements are pushed/added progressively (ie. to the top
of the stack), and unstacking/popping retrieves the element at the top
of the stack. Stacks can be implemented through arrays or linked lists.

#### Implementation
Array based stacks can use array progressively, adding the first element
to index 0 and and moving up the array's index as more elements are
added. To do so an implementation must track the index of the last
element inserted, as it is the index to retrieve on a pop. In order to
avoid the stack running out of space, the implemented array can be
dynamically size (ie. a vector).  
A linked list based stack must also track the last inserted node, but
faces no size limitations.

#### Average Complexities
| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(n)   | O(n)   | O(1)   | O(1)   |

**Notes on Complexity**
- Access and search are non standard operations in a stack. Both are
  O(n) if relying on stack operations. If implemented as operations of
  the underlying structure (either arrays ou linked lists), the
  complexity would match them as well.
- Insert is treated as a stack operation (pushing an element). Delete is
  treated as a pop. Both are array/linked list manipulation through
  indexes, so O(1).
  
#### Java Implementation
[Stack](https://docs.oracle.com/javase/7/docs/api/java/util/Stack.html)
class.

## Deque
#### What is it?
Short for Double Ended Queue. Elements can be added and removed from
both the front and the back of the deque. As such, a deque can be used
both as a queue (adding to end; removing from front) or as a stack (add
to end; remove from end). Can be implemented through arrays or linked
lists.

#### Implementation
Array based deques benefit from using a circular array in order to
optimize space complexity, much like an array based [queue](#queue).
Pointers track the front and back indexes, and a dynamic array can be
used instead of a static array in order to prevent the deque from
filling up.  
Linked List based deques do not face this optimization problem.

#### Average Complexities
| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(n)   | O(n)   | O(1)   | O(1)   |

**Notes on Complexity**
- Access and search are non standard operations in a deque. Both are
  O(n) if relying on deque operations. If implemented as operations of
  the underlying structure (either arrays ou linked lists), the
  complexity would match them as well.
- Insert and Delete are array/linked list manipulation through
  indexes, so O(1).
  
#### Java Implementation
[Deque](https://docs.oracle.com/javase/7/docs/api/java/util/Deque.html)
interface implemented through
[Array Deque](https://docs.oracle.com/javase/7/docs/api/java/util/ArrayDeque.html)
(for an array based deque) or
[Linked List](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html)
(for a linked list based deque).

## Heap   
#### What is it?
A Priority Queue is a data structure that can be implemented through
arrays (sorted or unsorted), linked lists, trees, and so forth.
Regardless of performance, a Priority Queue must always pop the most
extreme value it currently contains (maximum or minimum, calculated
through whatever criteria was set for the queue). However, performance
can vary depending on implementation.  
Heaps are a specific implementation of Priority Queues. Heaps are
represented as complete binary trees (all levels are full, except for
possibly the last one). As complete trees, they can be implemented
through an array instead of a tree - operational complexity is the same,
but trees have higher constant factors (a tree node takes more memory
than an array index).  
The most extreme value is always the root of the tree, which is valid
for all subtrees. MaxHeaps have the largest value at the root, and
MinHeaps the smallest.

#### Implementation
Heaps are implemented through arrays, being filled progressively. Given
arr[i] is an element of a heap with the root at index 0, the
relationship between indexes as tree nodes is expressed below:
- Parent Node: arr[ (i - 1) / 2 ]  
- Left Child: arr[ (2 * i) + 1 ]
- Right Child: arr[ (2 * i) + 2 ]   

The heap condition is enforced by "heapifying" values up or down the
tree on insert and on delete:
- Insert: value inserted at first empty index and "heapified up" -
  compared with the parent element and switched if more extreme (repeat
  until either not more extreme than parent or at root);
- Delete: value removed from root and value at last index moved to root,
  then "heapified down" - compared with both child elements and if less
  extreme, switched with the most extreme child (repeat until either not
  less extreme than any child or at leaf).  

#### Average Complexities
| Access | Search | Insert     | Delete     |
|--------|--------|------------|------------|
| O(1)   | O(n)   | O(log n)   | O(log n)   |

**Notes on Complexity**
- Access is usually performed only at root level to get the most extreme
  value.  
- Search is not a standard operation on a heap, since the only necessary
  element is the most extreme one. Other elements have to be searched by
  iterating through the array (O(n)).  
- Insert is always performed at the first empty index, which is O(1).
  However, it must then be "heapified up" the heap, making it O(log n).  
- Delete is usually only used at root level by removing the root and
  placing the last element of the heap at root. It is then heapified
  down, making it O(log n). Deleting an element other than root is a non
  standard operation which would be O(n), since the value would have to
  be searched first.     
  
#### Java Implementation
[PriorityQueue](https://docs.oracle.com/javase/7/docs/api/java/util/PriorityQueue.html)
class. Defaults to a MinHeap. To implement a MaxHeap, reverse the order
of the Collection :
```java
PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
```