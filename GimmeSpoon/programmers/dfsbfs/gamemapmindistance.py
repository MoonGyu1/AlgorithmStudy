import queue

def solution(maps):
    n, m = len(maps), len(maps[0])
    
    visited = set()
    visited.add((0, 0))
    q = queue.PriorityQueue()
    q.put((n + m - 1, 1, 0, 0))
    
    while not q.empty():
        _, e, r, c = q.get()
        if r == n - 1 and c == m - 1:
            return e
        for _r, _c in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
            if 0 <= _r < n and 0 <= _c < m and maps[_r][_c] == 1 and (_r, _c) not in visited:
                visited.add((_r, _c))
                q.put((n + m - _r - _c - 1 + e, e + 1, _r, _c))
                
    return -1