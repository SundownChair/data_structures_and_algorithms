# Table of Contents:
- [General Notes](#general-notes)
- [Select Sort](#select-sort)
- [Insert Sort](#insert-sort)

## General Notes
Sorting algorithms can be of 2 types regarding space used:
- In Place Sort: sorting is performed directly in the given data
  structure (no data is copied)
- Out of Place Sort: given data structure is copied to perform the sort

Sorting algorithms can be of 2 types regarding the order of duplicates:
- Stable Sort: order of duplicates after sorting is the same as the
  order before sorting
- Unstable Sort: original order of duplicates may be changed by sorting

## Select Sort
#### Algorithm
Given an unordered array a, and i is the current index, iterate for all
elements (last element may be omitted):
- Find the most extreme value (max/min) in subarray of a starting from i
- Swap most extreme value and element at i
```
 10 3 9 6 1   ->   1 3 9 6 10   ->   1 3 6 9 10
 swap(10,1)        swap(9,6)
```

#### Average Complexity
| Average | Worst   | Best    | Space Complexity     |
|---------|---------|---------|----------------------|
| O(n²)   | O(n²)   | O(n²)   | O(1)                 |

**Notes on Complexity**
- In Place sort

## Insert Sort
#### Algorithm
Given an unordered array a, and i is the current index, iterate for all
elements (except the first one):
- If the current index more extreme than the previous one, swap  
- If swap was performed, repeat for i - 1 as current value and i - 2 as
  new comparison. Repeat until either no swap is performed or index 0 is
  reached.
```
 10 5 1    -> 5 10 1    -> 5 1 10   ->   1 5 10
 swap(10,5)   swap(10,1)   swap(5,1)
```

#### Average Complexity
| Average | Worst   | Best    | Space Complexity     |
|---------|---------|---------|----------------------|
| O(n²)   | O(n²)   | O(n)   | O(1)                 |

**Notes on Complexity**
- In Place sort
- Best case is O(n) since each index is only compared to the previous.
  As such, this sort is best used on already sorted/mostly sorted data
  (ie: after inserting a new record in a previously ordered set)
