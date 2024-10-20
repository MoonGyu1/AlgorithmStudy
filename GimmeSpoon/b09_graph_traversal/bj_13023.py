import sys, collections, queue, copy

readline = sys.stdin.readline

# import random
# with open("input", "wt") as f:
#     f.write("2000 2000\n")
#     for _ in range(2000):
#         a, b = tuple(random.choices(list(range(2000)), k=2))
#         f.write(f"{a} {b}\n")

N, M = tuple(map(int, readline().split()))
friend = { i : {} for i in range(N)}

for _ in range(M):
    a, b = tuple(map(int, readline().split()))
    friend[a][b] = 1
    friend[b][a] = 1

def dfs (j, n, visited):
    if j == 5:
        return 1
    
    for m in friend[n]:
        if visited[m] == 0:
            visited[m] = 1
            ret = dfs(j + 1, m , visited)
            if ret == 1:
                return 1
            visited[m] = 0
    else:
        return 0

for a in friend.keys():
    visited = [0 for _ in range(N)]
    visited[a] = 1
    if dfs(1, a, visited) == 1:
        print(1)
        break
else:
    print(0)