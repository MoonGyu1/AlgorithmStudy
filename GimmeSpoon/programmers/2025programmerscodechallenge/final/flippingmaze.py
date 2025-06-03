def solution(visible, hidden, k):
    N, M = len(visible), len(visible[0])

    # Find the maximum points provided a grid map with weights
    def maze_points(maze, r=0, c=0, score=0, visited=None):
        if visited is None:
            visited = set()
        # target
        if (r, c) == (N - 1, M - 1):
            return score
        
        max_score = 0
        for _r, _c in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1))
            if 0 <= _r < N and 0 <= _c < M and (_r, _c) not in visited:
                visited.add((_r, _c))
                _score = maze_points(maze, _r, _c, score + maze[_r][_c], visited)
                if _score > max_score:
                    max_score = _score
                visited.remove((_r, _c))
        
        return max_score

    
