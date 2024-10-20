import io, os, sys

readline = io.BytesIO(os.read(0, os.fstat(0).st_size)).readline

n, k = tuple(map(int, readline().decode("utf-8").split()))

answer = 0

def teleport_coords(x):
    ret = []
    while x <= 100_000:
        ret.append(x)
        x = x << 1
    ret.append(x)
    return ret

def right_coords(x):
    ret = []
    if isinstance(x, int):
        ret.append(x + 1)
    else:
        for coord in x:
            if coord + 1 <= 100_000:
                ret.append(coord + 1)
    return ret

def left_coords(x):
    ret = []
    if isinstance(x, int):
        ret.append(x - 1)
    else:
        for coord in x:
            if coord - 1 >= 0:
                ret.append(coord - 1)
    return ret

if n >= k:
    print(n - k)
else:
    import queue, collections
    q = queue.PriorityQueue()
    visited = collections.defaultdict(int)
    q.put((0, n))
    visited[0] = 1

    while not q.empty():

        elapsed, x = q.get()

        if x == k:
            print(elapsed)
            break
        elif x > k:
            q.put((elapsed + x - k, k))
            continue

        # teleport
        next_coord = 2 * x
        if visited[next_coord] == 0:
                visited[next_coord] = 1
                q.put((elapsed, next_coord))
        # right
        next_coord = min(x + 1, 100000)
        if visited[next_coord] == 0:
            visited[next_coord] = 1
            q.put((elapsed + 1, next_coord))
        # left
        next_coord = max(x - 1, 0)
        if visited[next_coord] == 0:
            visited[next_coord] = 1
            q.put((elapsed + 1, next_coord))