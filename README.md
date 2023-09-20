## Calculating the Minimum Spanning Tree of NCAA Division 1 Schools

Step 1: Read file
 - File is formatted as the export file from a set of markers on Google maps.
 - Construct the nodes of the graph from those markers.
    - Nodes have a longitude, latitude, name, HashMap for edges in the graph along with the distance, and HashSet of connected nodes of the minimum spanning tree.

Step 2: Connect edges to make a graph
 - Connects each node to other nodes within a 6 degree square. This ensures that each node is connected to the closest set of nodes.
    - Special case for Hawaii since no nodes are within 6 degrees of longitude or latitude: connect to all west coast nodes.
 - Calculate the distance of these edges using the Great Circle Distance.

Step 3: Construct the minimum spanning tree
 - Use Prim's algorithm.
 - Store nodes in tree in a priority queue sorted by shortest non-explored edge.
 - Careful not to double count edges or make cycles.
 - Store edges within the MST in the HashSet for the node with lesser longitude along that edge.
    - i.e. if an edge in this MST connects nodes A and B and A.longitude < B.longitude, then store B in A's HashSet.

Step 4: Write a CSV
 - Formatted to be able to be imported into Google maps