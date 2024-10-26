import sys, queue

lines = sys.stdin.readlines()
N, M = tuple(map(int, lines[0].split()))
graph = {i : {} for i in range(N)}
for line in lines[1:]:
    a, b = tuple(map(int, line.split()))
    graph[a - 1][b - 1] = 0
    graph[b - 1][a - 1] = 0
    
def tree (paths, dist, start):
    q = queue.Queue()
    q.put((0, start))
    visited = [0 for _ in range(len(dist))]
    while not q.empty():
        d, n = q.get()
        if d < dist[n]:
            dist[n] = d
        for adj in paths[n].keys():
            if visited[adj] == 0:
                visited[adj] = 1
                q.put((d + 1, adj))

answer = 10000
build_pair = None
for small in range(N):
    for big in range(small + 1, N):
        distance = [10000 for _ in range(N)]

        tree(graph, distance, small)
        tree(graph, distance, big)

        sum_distance = sum(distance)

        if sum_distance < answer:
            answer = sum_distance
            build_pair = (small + 1, big + 1)

print(build_pair[0], build_pair[1], answer * 2)