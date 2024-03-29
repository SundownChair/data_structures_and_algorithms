# Table of Contents:
- [Depth First Search](#depth-first-search)
- [Breadth First Search](#breadth-first-search)
- [Single Source Shortest/Longest Path on DAG](#sssp)
- [Dijkstra's Shortest Path Algorithm](#dijkstra)
- [Bellman-Ford Shortest Path Algorithm](#bellman-ford)


## Depth First Search
#### Algorithm
Traversal algorithm that explores nodes and edges of a graphs. Traversal
 starts at a given root node, exploring as far as possible through each 
 branch before backtracking (and selecting another branch, if any are 
 left unvisited).

DFS takes one node "at" and a collection "visited", recursively call:
```
if(visited[at]) {return}
  
visited[at] = true
	
for(next in at.connectedNodes)
  dfs(next)
```

#### Average Complexity
| Average               |
|-----------------------|
| O(vertices + edges)   |



## Breadth First Search
#### Algorithm
Traversal algorithm that explores nodes and edges of a graph in a 
layered fashion. Traversal starts at a given root node, queueing it's 
child connected nodes to be explored (if not already visited or queued),
 visiting queued nodes in order while queueing their connected and 
 unvisited nodes, until no more nodes exist or are left unvisited.  
It's particularly useful to find the shortest path on unweighted graphs.

bfs takes one node "at", a collection "visited" and a queue "q", call:
```
visited[at] = true
q.queue(at)
		
while(!q.isEmpty)
  node = q.dequeue()
  neighbours = g.getNeighbours(node)
			
  for(next : neighbours)
    if(!visited[next])
      q.queue(next)
      visited[next] = true
```



#### Average Complexity
| Average               |
|-----------------------|
| O(vertices + edges)   |

## <a name='sssp'/>Single Source Shortest//Longest Path on DAG
#### Algorithm
In Directed Acyclic Graphs, the single source shortest path problem can
be solved in linear time if nodes are topologically ordered and then
processed sequentially. The same is true for the longest path, since the
edge weights simply need to be "flipped" (multiplied by -1) during
processing to achieve the desired path (for general graphs, the same
problems is NP-Hard).

SSSP takes a graph "graph" and a start node "start", call:
```
int[] topSort = topSort(graph)
int[] dist = new int[graph.numOfNodes()]
dist[start] = 0

for (node in topSort) // nodes that start cannot link to can be skipped
  currentNode = graph[i]
  for(n : currentNode.getNeighbours())
    totalDist = dist[node] + graph.getEdgeWeight(node, n)
    if(dist[n] == null)
      dist[n] = totalDist
    else if(isMoreExtreme(totalDist, dist[n])) // totalDist > dist[n] for SSSP
      dist[n] = totalDist                      // totalDist < dist[v] for SSLP

return dist
```

#### Average Complexity
| Average               |
|-----------------------|
| O(vertices + edges)   |



## <a name='dijkstra'/>Dijkstra's Shortest Path Algorithm
#### Algorithm
Dijkstra's algorithm is a Single Source Shortest Path algorithm for
graphs with non-negative edge weights. A start node must be specified,
and the shortest distance between the given node and all other nodes in
the algorithm will be returned when finished.  
This is a greedy algorithm that always selects the next most promising
node, which is only possible since there are no non-negative weighted
edges.  

Given a graph 'g' with n elements, and starting node 's', call:
```
visited = [false, ..., false] // n length
previous = [null, ..., null] // n length, used to rebuild best path to given node
distance = [null, ..., null] // n length
distance[s] = 0
queue = empty indexed priority queue
queue.add(s, 0)
while(!queue.isEmpty())
  node, bestDistance = queue.poll()
  visited[node] = true
  if distance[node] < bestDistance
    continue
  for(childNode : g.getLinkedNodes(node))
    if visited[childNode]
      continue
    newDistance = distance[node] + g.getDistance(node, childNode)
    if distance[childNode] == null || newDistance < distance[childNode]
      distance[childNode] = newDistance
      previous[childNode] = node
      if !queue.contains(childNode)
        queue.add(childNode, newDistance)
      else
        queue.updateKey(childNode, newDistance) // updates value if better than existing
return distance
```

#### Average Complexity
| Average                    |
|----------------------------|
| O(edges * log(vertices))   |

**Notes on Complexity**
- A priority queue is used to track the next most promising node to visit,
but the type of priority queue greatly affects the performance and
complexity of the algorithm. A simple priority queue will have a time
complexity of O(edges * log(vertices)), but drops of in dense graphs
since it will contain many duplicated entries for a given node where all
but one will be the most promising - this can be solved by
removing/updating data, but updating a priority queue node is an O(n)
operation. An indexed priority queue can solve the duplication issue by
updating nodes in O(log n) time, resulting in an eager implementation
that will not contain duplicate entries for a given node, only the most
promising one - time complexity remains O(edges * log(vertices)), but
drop-off is lessened for dense graphs. Using a D-ary Heap will further
improve efficiency since it speeds up updates by lowering the overall
depth of the heap (optimal D is generally edges/vertices) - this results
in O(edges * log(edges/vertices) (vertices)). Using a Fibonacci Heap can
bring time complexity to O(edges + vertices * log(vertices)), but the
increased complexity and constant amortized overhead would make it
impractical for smaller graphs.



## <a name='bellman-ford'/>Bellman-Ford Shortest Path Algorithm
#### Algorithm
The Bellman-Ford algorithm is a Single Source Shortest Path algorithm. 
Time complexity is worse Dijkstra's (especially if using implementations
such as indexed priority queues or Fibonacci heaps), but Bellman-Ford 
can handle negative edge weights (which cause Dijkstra's to fail in an
infinite loop). Negative edges can create infinite cycles or affect
other nodes, placing them in a negative cycle as well.


Given a graph 'g' with n elements, and starting node 's', call:
```
// account for possible overflows in all sums with +∞/-∞ 
numVertices = g.numOfVertices()
dist = [+∞, ..., +∞] // n length
dist[s] = 0
	
// First cycle relaxes edges to the shortest path possible
for(i = 0; i < numVertices-1; i++)
  for edge in graph.getEdges()
    if(dist[edge.from] + edge.cost < dist[edge.to])
      dist[edge.to] = dist[edge.from] + edge.cost
	
// Second cycle find nodes in a negative cycle 
// Any update performed on the optimal path marks a negative cycle
for(i = 0; i < numVertices-1; i++)
  for edge in graph.getEdges()
    if(dist[edge.from] + edge.cost < dist[edge.to])
      dist[edge.to] = -∞ // node is "unreachable"
	
return dist
```

#### Average Complexity
| Average               |
|-----------------------|
| O(edges * vertices)   |
