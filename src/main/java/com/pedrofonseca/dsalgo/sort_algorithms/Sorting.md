# Table of Contents:
- [General Notes](#general-notes)
- [Select Sort](#select-sort)

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