import sys, queue
from collections import defaultdict

inp = sys.stdin.readlines()

N, M = int(inp[0]), int(inp[1])

graph = defaultdict(lambda : defaultdict(lambda : -1))
for l in inp[2:-1]:
    s, e, c = list(map(int, l.split()))
    if graph[s][e] == -1 or graph[s][e] > c:
        graph[s][e] = c

s, e = list(map(int, inp[-1].split()))

q = queue.PriorityQueue()
q.put((0, s))
min_costs = {n : -1 for n in range(1, N + 1)}
min_costs[s] = 0

while not q.empty():
    _, n = q.get()
    for dest, cost in graph[n].items():
        if min_costs[dest] < 0:
            min_costs[dest] = cost + min_costs[n]
            q.put((min_costs[dest], dest))
        elif cost + min_costs[n] < min_costs[dest]:
            min_costs[dest] = cost + min_costs[n]

print(min_costs[e])