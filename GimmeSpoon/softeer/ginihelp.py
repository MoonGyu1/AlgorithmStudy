import sys, queue

inp = sys.stdin.readlines()
R, C = list(map(int, inp[0].split()))
grid = inp[1:]
weather_map = [[R * C for _ in range(C)] for _ in range(R)]

q = queue.Queue()
for r, row in enumerate(grid):
    for c, cell in enumerate(row):
        if cell == "W":
            start = (r, c)
        elif cell == "H":
            target = (r, c)
        elif cell == "X":
            weather_map[r][c] = -1
        elif cell == "*":
            weather_map[r][c] = 0
            q.put((r, c, 0))

while not q.empty():
    r, c, t = q.get()
    for nr, nc in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
        if 0 <= nr < R and 0 <= nc < C:
            if t + 1 < weather_map[nr][nc] and grid[nr][nc] != "H":
                weather_map[nr][nc] = t + 1
                q.put((nr, nc, t + 1))

q = queue.PriorityQueue()
q.put((0, start[0], start[1]))
visited = {(start[0], start[1]) : 0}

while not q.empty():
    t, r, c = q.get()
    for nr, nc in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
        if (nr, nc) == target:
            print(t + 1)
            break
        if 0 <= nr < R and 0 <= nc < C and weather_map[nr][nc] > t + 1:
            if (nr, nc) not in visited or visited[(nr, nc)] > t + 1:
                visited[(nr, nc)] = t + 1
                q.put((t + 1, nr, nc))
    else:
        continue
    break
else:
    print("FAIL")
    