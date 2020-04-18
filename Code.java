class Solution {
    /*
        This solution based on DFS of graph algorithm
        Consider the grid to be a graph, with each node is the "land", edges are horizontal and vertical links
        We count number of different disjoints connected set of nodes in the graph
    */
    
    boolean[][] visited;
    
    public int numIslands(char[][] grid) {
        if(grid.length == 0)
            return 0;
        
        visited = new boolean[grid.length][grid[0].length];
        
        int count = 0;
        
        ArrayList<Node> listNode = new ArrayList<>();
        
        for(int i=0; i < grid.length; i++)
            for(int j=0; j < grid[0].length; j++){
                if(grid[i][j] == '1'){
                    listNode.add(new Node(i,j));
                }
            }
        
        for(Node startNode : listNode){
            int curRow = startNode.row();
            int curCol = startNode.col();
            Stack<Node> stack = new Stack<>();
            
            if(!visited[curRow][curCol]){
                // if it is unvisited, and is the startNode, then this is a new set of connected node
                // because if this node belongs to another connected set that we has seen so far,
                // it must be visited from the previous DFS 
                count ++;  
                stack.push(new Node(curRow,curCol));
                visited[curRow][curCol] = true;
            }

            while(!stack.isEmpty()){
                curRow = stack.peek().row();
                curCol = stack.peek().col();
                visited[curRow][curCol] = true;


                if(curCol > 0 && !visited[curRow][curCol-1]){
                    if(grid[curRow][curCol-1] == '1'){
                        stack.push(new Node(curRow, curCol-1));
                        continue;
                    }
                }
                if(curCol < grid[0].length-1 && !visited[curRow][curCol+1]){
                    if(grid[curRow][curCol+1] == '1'){
                        stack.push(new Node(curRow, curCol+1));
                        continue;
                    }
                }
                if(curRow > 0 && !visited[curRow-1][curCol]){
                    if(grid[curRow-1][curCol] == '1'){
                        stack.push(new Node(curRow-1, curCol));
                        continue;
                    }
                }
                if(curRow < grid.length-1 && !visited[curRow+1][curCol]){
                    if(grid[curRow+1][curCol] == '1'){
                        stack.push(new Node(curRow+1, curCol));
                        continue;
                    }
                }

                stack.pop();
            }
        }
        
        return count;
            
    }

    
    // define the lands
    private class Node{
        int i, j;
        
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
        
        int row(){
            return i;
        }
        
        int col(){
            return j;
        }
    }
}
