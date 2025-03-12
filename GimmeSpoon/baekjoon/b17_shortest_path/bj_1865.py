import sys

def shortestpath(num_nodes:int, edges:list, src:int, tgt:int):

    distances = {u : 5_000_000_000 for u in range(1, num_nodes + 1)}
    distances[src] = 0

    for _ in range(num_nodes - 1):
        for u, v, t in edges:
            if distances[u] + t < distances[v]:
                distances[v] = distances[u] + t
    
    for u, v, t in edges:
        if distances[u] + t < distances[v]:
            return None

    return distances[tgt]

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
    for s, e, t in edges[wh_index:]:
        d = shortestpath(N, edges, e, s)
        if d is None or d < -t:
            print("YES")
            break
    else:
        print("NO")