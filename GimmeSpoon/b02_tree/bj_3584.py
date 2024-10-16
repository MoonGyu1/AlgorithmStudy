import sys, io, os
from collections import defaultdict

stdin = io.BytesIO(os.read(0, os.fstat(0).st_size))

T = int(stdin.readline())

for _ in range(T):
    parent = defaultdict(int)
    N = int(stdin.readline())
    for _ in range(N - 1):
        a, b = list(map(int, stdin.readline().split()))
        parent[b] = a
    a, b = list(map(int, stdin.readline().split()))
    visited = [0 for _ in range(N + 1)]
    while a != b:
        if visited[a] == -1:
            print(a)
            break
        if a != 0:
            visited[a] = 1
            a = parent[a]
        if visited[b] == 1:
            print(b)
            break
        if b != 0:
            visited[b] = -1
            b = parent[b]
    else:
        print(a)