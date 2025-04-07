import sys

def negativecycle(num_nodes:int, edges:list, src:int):

    distances = {u : 5_000_000_000 for u in range(1, num_nodes + 1)}
    distances[src] = 0

    for _ in range(num_nodes - 1):
        for u, v, t in edges:
            if distances[u] + t < distances[v]:
                distances[v] = distances[u] + t
    
    for u, v, t in edges:
        if distances[u] + t < distances[v]:
            return True

    return False

TC = int(sys.stdin.readline())

for _ in range(TC):
    N, M, W = list(map(int, sys.stdin.readline().split()))
    edges = []
    for _ in range(M):
        S, E, T = list(map(int, sys.stdin.readline().split()))
        edges.append((S, E, T))
        edges.append((E, S, T))
    wh_index = len(edges)
    for _ in range(W):
        S, E, T = list(map(int, sys.stdin.readline().split()))
        edges.append((S, E, -T))

    if negativecycle(N, edges, 1):
        print("YES")
    else:
        print("NO")
