import sys, collections, queue

inp = list(map(str.strip, sys.stdin.readlines()))
H, W = list(map(int, inp[0].split()))
grid = inp[1:]

directions = ["^", ">", "v", "<"]
delta_for_direction = [
    [(-1, 0), (-2, 0)],
    [(0, 1), (0, 2)],
    [(1, 0), (2, 0)],
    [(0, -1), (0, -2)],
]

start = (0, 0)
start_direction = None
for r, row in enumerate(grid):
    for c, v in enumerate(row):
        if v == "#" and collections.Counter([
            grid[nr][nc] if 0<= nr < H and 0 <= nc < W else "." for nr, nc in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1))
        ])["."] == 3:
            start = (r, c)
            break
    else:
        continue
    break

q = queue.Queue()
q.put((*start, None))
visited = set()
visited.add(start)
path = []

while not q.empty():
    r, c, d = q.get()
    
    for nr, nc, nd in ((min(r + 1, H - 1), c, 2), (max(r - 1, 0), c, 0), (r, min(c + 1, W - 1), 1), (r, max(c - 1, 0), 3)):
        if grid[nr][nc] == "#" and (nr, nc) not in visited:
            
            if d is None:
                start_direction = nd
            else:
                dd = nd - d
                dc = "R"
                if dd == 3:
                    dd = -1
                elif dd == -3:
                    dd = 1
                if dd < 0:
                    dd = -dd
                    dc = "L"
                path.append(dc * dd)
                
            path.append("A")
            visited.add((r + delta_for_direction[nd][0][0], c + delta_for_direction[nd][0][1]))
            visited.add((r + delta_for_direction[nd][1][0], c + delta_for_direction[nd][1][1]))
            q.put((r + delta_for_direction[nd][1][0], c + delta_for_direction[nd][1][1], nd))

print(start[0] + 1, start[1] + 1)
print(directions[start_direction])
print("".join(path))
            