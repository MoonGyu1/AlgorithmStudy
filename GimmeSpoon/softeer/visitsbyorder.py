import sys, queue, copy

inp = sys.stdin.readlines()
n, m = list(map(int, inp[0].split()))
grid = list(map(lambda x: list(map(int, x.split())), inp[1:n + 1]))
target_list = list(map(lambda x: tuple(map(int, x.split())), inp[n + 1:]))
targets = {
    (r, c) : i
    for i, (r, c) in enumerate(target_list)
}
visited = set([target_list[0]])
q = queue.Queue()
q.put((target_list[0], visited, 1))

num_paths = 0
while not q.empty():
    (r, c), v, t = q.get()
    
    for nr, nc in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
        if 0 < nr <= n and 0 < nc <= n and grid[nr - 1][nc - 1] == 0 and (nr, nc) not in v:
            if (nr, nc) in targets:
                if targets[(nr, nc)] == t:
                    if t == m - 1:
                        num_paths += 1
                    else:
                        _v = copy.deepcopy(v)
                        _v.add((nr, nc))
                        q.put(((nr, nc), _v, t + 1))
            else:
                _v = copy.deepcopy(v)
                _v.add((nr, nc))
                q.put(((nr, nc), _v, t))

print(num_paths)