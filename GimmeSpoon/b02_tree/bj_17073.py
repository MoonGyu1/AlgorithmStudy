import sys
from collections import defaultdict

readline = sys.stdin.readline

N, W = readline().split()
N, W = int(N), int(W)

num_edges = N - 1
edges = defaultdict(int)
for _ in range(N - 1):
    u, v = readline().split()
    u, v = int(u), int(v)
    edges[u] += 1
    edges[v] += 1
    if u != 1 and edges[u] == 2:
        num_edges -= 1
    if v != 1 and edges[v] == 2:
        num_edges -= 1

print(W / num_edges)
