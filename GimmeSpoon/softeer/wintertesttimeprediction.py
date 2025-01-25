import sys, queue, collections

N, M = list(map(int, sys.stdin.readline().split()))
grid = list(map(str.split, sys.stdin.readlines()))

ice = collections.defaultdict(int)

visited = set()
visited.add((0, 0))

q = queue.PriorityQueue()
q.put((0, 0, 0))

maxt = 0
melted = 0

while not q.empty():
    
    t, r, c = q.get()

    if t > maxt:
        maxt = t
        
    for nr, nc in ((max(0, r - 1), c), (min(N - 1, r + 1), c), (r, max(0, c - 1)), (r, min(M - 1, c + 1))):
        if grid[nr][nc] == "1":
            if ice[(nr, nc)] < 2: # ice
                ice[(nr, nc)] += 1
                if ice[(nr, nc)] == 2:
                    q.put((t + 1, nr, nc))
        elif (nr, nc) not in visited:
            visited.add((nr, nc))
            q.put((t, nr, nc))

print(maxt)