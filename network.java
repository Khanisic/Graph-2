// Time Complexity : O(v+e)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    List<List<Integer>> result;
    List<List<Integer>> graph; // hashmap, adjacency matrix
    int[] discovery;
    int[] lowest;
    int time;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        if (n == 0) {
            return new ArrayList<>();
        }
        result = new ArrayList<>();
        graph = new ArrayList<>();
        discovery = new int[n];
        lowest = new int[n];
        Arrays.fill(discovery, -1);
        Arrays.fill(lowest, -1);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>()); // creates a new list, at every index has an empty list.
        }
        buildGraph(connections);
        dfs(0, -1);
        return result;
    }

    private void buildGraph(List<List<Integer>> connections) {
        for (List<Integer> connection : connections) {
            int from = connection.get(0);
            int to = connection.get(1);
            graph.get(from).add(to);
            graph.get(to).add(from);
        }
    }

    private void dfs(int v, int u) { // u is parent, where we came from and v is where we are going.
        // base
        if (discovery[v] != -1) {
            return;
        }
        // logic
        discovery[v] = time;
        lowest[v] = time;
        time++;
        List<Integer> children = graph.get(v);
        for (int n : children) { // iterating over all the children
            if (n == u) { // came from parent, no sense going back.
                continue; // ignore the entire loop
            }
            dfs(n, v); // going to node n from v
            if (lowest[n] > discovery[v]) { // current lowest greater than time of discovering this node
                result.add(Arrays.asList(n, v));
            }
            lowest[v] = Math.min(lowest[v], lowest[n]); // updating the lowest of each node.
        }
    }
}