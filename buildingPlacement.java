// Time Complexity : O(hw * hw c n )
// Space Complexity : O(hw)

class buildingPlacement {
    public static void main (String[] args) {
        BuildingPlacement buildingPlacement = new BuildingPlacement();
        System.out.print(buildingPlacement.findMinDistance(5, 4, 2));
    }

    public static class BuildingPlacement {
        int minDistance = Integer.MAX_VALUE;
        public int findMinDistance(int h, int w, int n){
            int[][] grid = new int[h][w];
            for(int i =0; i<h; i++){
                for(int j=0; j<w; j++){
                    grid[i][j] = -1;
                }
            }
            backtrack(grid, 0, 0, h, w, n);
            return minDistance;
        }

        // breadth first for finding minDistance of each combination
        private void bfs(int[][] grid, int h, int w){
            boolean[][] visited = new boolean[h][w];
            int[][] dirs = {{-1,0},{0,-1},{0,1},{1,0}};
            Queue<int[]> q = new LinkedList<>();
            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(grid[i][j]==0){
                        q.add(new int[]{i,j});
                        visited[i][j] = true;
                    }
                }
            }
            int dist = 0;
            while(!q.isEmpty()){
                int size = q.size();
                for(int i=0; i<size; i++){
                    int[] curr = q.poll();
                    for(int[] dir: dirs){
                        int nr = dir[0] + curr[0];
                        int nc = dir[1] + curr[1];
                        if(nr>=0 && nc>=0 && nr<h && nc<w && !visited[nr][nc]){
                            q.add(new int[]{nr, nc});
                            visited[nr][nc] = true;
                        }
                    }
                }
                dist++;
            }
            minDistance = Math.min(minDistance, dist-1);
        }

        private void backtrack(int[][] grid, int r, int c, int h, int w, int n){
            //base
            if(n==0){
                bfs(grid, h, w);
                return;
            }

            //logic
            if(c == w){
                c=0; r++;
            }
            for(int i=r; i<h; i++){
                for(int j=c; j<w; j++){
                    grid[i][j] = 0;
                    //recurse
                    backtrack(grid, i, j+1, h, w, n-1);
                    //backtracking
                    grid[i][j] = -1;
                }
                c=0;
            }
        }
    }
}