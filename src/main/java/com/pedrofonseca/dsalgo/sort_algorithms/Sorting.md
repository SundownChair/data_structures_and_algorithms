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
- Outperforms bubble sort, and usually underperforms insert sort (always
  O(n²) performance)

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
| O(n²)   | O(n²)   | O(n)    | O(1)                 |

**Notes on Complexity**
- In Place sort
- Best case is O(n) since each index is only compared to the previous.
  As such, this sort is best used on already sorted/mostly sorted data
  (ie: after inserting a new record in a previously ordered set)

## Shell Sort
Variation of Insert Sort that swaps values with a decreasing gap. Since
it prioritizes exchanging extremities, it can move very out of place
values into position faster than Insert Sort.

#### Algorithm
Given an unordered array n, i being the current index and m the starting
gap:
- For value pair [i, i+m], swap indexes if i+m is more extreme than i
- Increase i and iterate through n while i+m is a valid index of n
- When n is traversed, decrease m and start from first step. Iterate
  while m > 1
  
**Notes on Algorithm**
- As a baseline, m can start as n.length/2 and decrease to half it's
  size for every iteration

#### Average Complexity
| Average      | Worst        | Best        | Space Complexity |
|--------------|--------------|-------------|------------------|
| O(n log(n)²) | O(n log(n)²) | O(n log(n)) | O(1)             |

**Notes on Complexity**
- In Place sort
- Best case is O(n log(n)) assumes an already sorted array

## Bubble Sort
Simple sort that iterates through all elements in an array, switching
any contiguous elements that are unordered. Will perform complete passes
through the array until no switches are made. The first pass will
assuredly place the most extreme element in the last position; the
second pass, the second most extreme in the second to last position, and
so forth until the array is ordered.

#### Algorithm
Given an unordered array a, and i is the current index, iterate through
all elements except the last:
- Compare element at i to i+1
- If next element is more extreme, swap elements
- Repeat for all elements, and repeat passes until no swaps are made for
  a full pass
  
#### Complexity
| Average      | Worst        | Best        | Space Complexity |
|--------------|--------------|-------------|------------------|
| O(n²)        | O(n²)        | O(n)        | O(n)             |

**Notes on Complexity**
- In Place sort
- Best case is an already sorted or almost sorted array
- Don't use this algorithm, it has terrible performance

## Merge Sort
Divide given list in half  
Stable, but out of place (requires copy of the data)