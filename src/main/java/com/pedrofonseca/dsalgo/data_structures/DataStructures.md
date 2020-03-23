Here is a list of common data structure implementations, along 
with my notes on them.

# Table of Contents:
- [Array](#array)
- [Vector](#vector)
- [Queue](#queue)
- [Stack](#stack)
- [Deque](#deque)
- [Heap](#heap)
- [LinkedList](#linked-list)
- [Trees](#trees)
- [Binary Search Tree](#binary-search-tree)
- [AVL Tree](#avl-tree)

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

## Linked List
#### What is it?
Linked Lists are a linear, non contiguous data structure where elements
(called nodes) are "linked" through pointers. Every node stores its own
value and a pointer to the next node in the list. The header of a linked
list is a pointer to its first node, and some implementations can also
have a tail pointer at the last node.  
Unlike arrays, linked lists have no fixed size (nodes can be
continuously added), and insertion/deletion does not transpose other
elements - only node pointers must be updated (ie: inserting node B
after node A means that A's next pointer must be updated to B), and
eventually the head or tail pointer as well. However, access to nodes is
sequential (unlike an array's random access), and a node will take up
more space than an array's index (note that an array will take up space
for its entire capacity, assigned or not; linked lists only contain
assigned elements).  
A Doubly Linked List has each node have a pointer to its next and
previous node. This means that they can be traversed forwards and,
unlike Singly Linked Lists, backwards. Space consumption is higher due
to the extra pointer, but access to a node's previous node is
simplified.  
Rule of thumb: arrays are preferred when accesses are more frequent, and
linked lists when insertions/deletions are more frequent (especially mid
list operations).

#### Average Complexities
| Access | Search | Insert | Delete |
|--------|--------|--------|--------|
| O(n)   | O(n)   | O(1)   | O(1)   |

**Notes on Complexity**
- Search and access is O(n) because traversal is sequential, so the list
  must be traversed linearly.
- Insert and delete assume that the position to insert has already been
  found (otherwise list traversal is O(n)). 

#### Java Implementation
[LinkedList](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedList.html)
implementation of the
[List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html)
interface (implements a Doubly Linked List).  
[LinkedHashMap](https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html)
and
[LinkedHashSet](https://docs.oracle.com/javase/7/docs/api/java/util/LinkedHashSet.html)
also implement LinkedList (as well as
[HashMap](https://javadoc.scijava.org/Java/java/util/HashMap.html) and
[HashSet](https://docs.oracle.com/javase/7/docs/api/java/util/HashSet.html),
respectively). However, consider the different complexities and
behaviours of hash maps and hash sets.

## Trees
#### What are they?
Trees are datastructures that represent and organize data in linked
nodes, similarly to linked lists. Unlike linked lists, tree nodes can
have more than one node linked to them, in a parent-child relationship.
All trees have a root, a parent node from where the tree effectively
"starts". Trees where nodes can have at most 2 children nodes (left and
right) are called binary trees, for instance.  
Complete trees are trees where elements are filled in from left to right
in all populated levels. Heaps are by definition complete trees.  
Full trees are tree that are complete and where all populated levels are
completely filled.  

#### Variations
Besides common data structures (Binary Search Trees, AVL Trees,
Red-Black Trees, etc), other specialized structures can be created using
trees. One example would be Expression Trees, which can both read, write
and solve postfix expressions.

## Binary Search Tree
#### What is it?
A type of binary tree (where each node has at most 2 children and
exactly one parent) where the following condition is applied to the tree
and any existing sub trees: left node < root node < right node.  
Due to the condition above, a balanced binary search tree can be
searched like a sorted array is via binary search (eliminating half the
search space with every operation), bue due to the nature of nodes, it
will present better performance for inserting/deleting records.

#### Average Complexities
| Access     | Search     | Insert     | Delete     |
|------------|------------|------------|------------|
| O(log n)   | O(log n)   | O(log n)   | O(log n)   |

**Notes on Complexity**
- The above assumes that the tree is relatively balanced, making its
  height close to log(n). However, order of insertion will affect the
  performance of all of the above operations. A linear tree will have a
  height of n, which will make all operations have an average complexity
  of O(n).
  
#### Tree Traversal
###### Pre-Order Traversal (depth first):
- Visit root node
- Visit left node
- Visit right node

###### In-Order Traversal (depth first):
This will produce an sorted traversal of the elements of the array. 
- Visit left node
- Visit root node
- visit right node

###### Post-Order Traversal (depth first):
- Visit left child
- Visit right child
- Visit root

###### Level Order Traversal (breadth first):
This will traverse the tree level by level, only starting the next level
after the previous has been completely traversed. Starting from the
root, place it in a queue and iterate the following:
- Pop element from queue
- Check element
- Push child nodes into queue
- Repeat until queue is empty

#### Rotations
A Binary Search Tree's nodes can, in certain contexts, be "rotated"
while keeping the BST condition. This is particularly important if one
is trying to keep a tree balanced without having to rebuild it after
every change.  
There are four possible rotation scenarios that can affect the balance
of a BST:  
###### Left Rotation (RR case)
``` 
A┐    
 B┐ =>  ┌B┐
  C     A C
```
A left rotation makes a node's right child its parent (effectively
"rotating" the node to the left of the child).

######  Right Rotation (LL case)
```
 ┌A
┌B  =>  ┌B┐
C       C A
```
A right rotation makes a node's left child its parent (effectively
"rotating" the node to the right of the child).  

###### Right Left Rotation (RL case)
```
A┐      A┐       
┌B  =>   C┐  =>  ┌C┐
C         B      A B
```
A right rotation of the node's child followed by a left rotation of the
node.

###### Left Right Rotation (LR case)
```
┌A       ┌A      
B┐  =>  ┌C   =>  ┌C┐
 C      B        B A
```
A left rotation of the node's child followed by a righht rotation of the
node.

#### Java Implementation
[TreeMap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)
and
[TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html).
Both function like a Red-Black Tree, ordered and self balancing - as
such, can be used as simple BSTs. TreeMap accepts <key, value> pairs
(implements
[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)) and
TreeSet accepts only values (implements 
[Set](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html)).

## AVL Tree
#### What is it?
Self balancing binary tree based on tree height (see below). For the
root or any subtree, the difference between the left and right nodes'
height may not be larger than one - if so, the tree is said to be
unbalanced. After every operation that changes the structure of the tree
(insertion/deletion/etc), if the tree is left unbalanced, the nodes
where the height criteria is not met are rotated until they are
compliant.  
Compared to a Red-Black Tree, insertions/deletions are more complex
because the tree must always be balanced, which in turn makes searches
more efficient. Rule of thumb: AVL Trees are to be used when search
performance is more important than insertion/deletion performance.

#### Node Height and Balance Factor
The height of a node is the longest path from any connected leaf node to
it. It is represented as the largest height between its left and right
subtree +1 (leaf nodes are considered as having a height of -1).  
> height(n) = max(n.left, n.right) + 1  

The Balance Factor of a node is the difference of a node's left and
right subtree's heights. For an AVL Tree, if the balance of a node is
larger than 1, the node is "left heavy"; if the balance is smaller than
\-1, the node is "right heavy" - in both cases, rebalancing must occur.
> balanceFactor(n) = n.left - n.right 

#### Average Complexities
| Access     | Search     | Insert     | Delete     |
|------------|------------|------------|------------|
| O(log n)   | O(log n)   | O(log n)   | O(log n)   |

**Notes on Complexity**
- Unlike a normal BST, complexity is constant for an AVL Tree due to
  constant rebalancing.

#### Rebalancing
Depending on the Balance Factor of a node, different rotations have to
be applied to rebalance the tree. Four cases can occur:
- **Node Balance < -1 and Right Child Balance < 0:** Left Rotation of
  Node
- **Node Balance < -1 and Right Child Balance > 0:** Right Rotation of
  Right Child and Left Rotation of Node
- **Node Balance > 1 and Left Child Balance > 0:** Right Rotation of
  Node
- **Node Balance > 1 and Left Child < 0:** Left Rotation of Left Child
  and Right Rotation of Node

So, for Node n:
```java
if (n.balance < -1) {
    c = n.right;
    if (c.balance < 0) {
        leftRotation(n);
    } else if (c.balance > 0) {
        rightRotation(c);
        leftRotation(n);
    }
} else if (n.balance > 1) {
    c = c.left;
    if (c.balance > 0) {
        rightRotation(n);
    } else if (c.balance < 0) {
        leftRotation(c);
        rightRotation(n);
    }
}
``` 

#### Java Implementation
[TreeMap](https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html)
and
[TreeSet](https://docs.oracle.com/javase/7/docs/api/java/util/TreeSet.html).
Both function like a Red-Black Tree, ordered and self balancing - as
such, can be used as simple BSTs. TreeMap accepts <key, value> pairs
(implements
[Map](https://docs.oracle.com/javase/8/docs/api/java/util/Map.html)) and
TreeSet accepts only values (implements 
[Set](https://docs.oracle.com/javase/7/docs/api/java/util/Set.html)).

