import sys, queue, copy

inp = list(map(int, sys.stdin.read().split()))
N = inp[0]
garages = [inp[n:0:-N] for n in range(-N, 0)]

def dfs(garage, acc = 0, round = 1):
    
    visited = set()
    max_score = 0
    
    for c in range(N):
        for r in range(N):
            
            if (r, c) not in visited:

                score = 1
                w1, w2, h1, h2 = c, c, r, r
                
                local_group = [queue.PriorityQueue() for _ in range(N)]
                local_group[c].put(r)
                
                q = queue.Queue()
                q.put((r, c))
                visited.add((r, c))
                
                while not q.empty():
                    r, c = q.get()
                    for nr, nc in ((min(r + 1, N - 1), c), (max(r - 1, 0), c), (r, min(c + 1, N - 1)), (r, max(c - 1, 0))):
                        if (nr, nc) not in visited and garage[nc][nr] == garage[c][r]:
                            q.put((nr, nc))
                            visited.add((nr, nc))
                            score += 1
                            if round < 3:
                                local_group[nc].put(nr)
                            if h1 > nr:
                                h1 = nr
                            if h2 < nr:
                                h2 = nr
                            if w1 > nc:
                                w1 = nc
                            if w2 < nc:
                                w2 = nc

                score += (h2 - h1 + 1) * (w2 - w1 + 1)

                if round < 3:
                    _garages = []

                    for c in range(N):
                        column = []
                        _r = -1
                        while not local_group[c].empty():
                            r = local_group[c].get()
                            column += garage[c][_r + 1:r]
                            _r = r
                        column += garage[c][_r + 1:]
                        _garages.append(column)
                        
                    score = dfs(_garages, acc + score, round + 1)
                else:
                    score = score + acc

                if max_score < score:
                    max_score = score
                    
    return max_score

if N == 1:
    print(6)
else:
    print(dfs(garages))