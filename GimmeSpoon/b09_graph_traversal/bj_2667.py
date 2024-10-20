import io, os, sys, queue

readlines = sys.stdin.readlines

grid = list(map(lambda x: list(map(int, x.strip())), readlines()[1:]))
visited = [[0 for _ in range(len(grid[0]))] for _ in range(len(grid))]
R, C = len(grid) - 1, len(grid[0]) - 1

houses = queue.PriorityQueue()
for r, row in enumerate(grid):
    for c, house in enumerate(row):
        if house:
            houses.put((0, r, c))

town = {}
current_town = 0
while not houses.empty():
    t, r, c = houses.get()

    if visited[r][c] == 1:
        continue
    if t == 0:
        current_town -= 1
        town[current_town] = 1
    else:
        town[current_town] += 1

    visited[r][c] = 1

    next_coords = ((r, max(c-1, 0)), (max(r-1, 0), c), (r, min(c+1, C)), (min(r+1, R), c))
    for tr, tc in next_coords:
        if visited[tr][tc] == 0 and grid[tr][tc] == 1:
            houses.put((current_town, tr, tc))

print(len(town))
for v in sorted(list(town.values())):
    print(v)
