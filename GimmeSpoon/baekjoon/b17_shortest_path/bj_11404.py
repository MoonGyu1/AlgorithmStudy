import sys

inp = sys.stdin.readlines()
n, m = int(inp[0]), int(inp[1])

costs = [[1_000_000_000 for _ in range(n)] for _ in range(n)]

for line in inp[2:]:
    s, e, c, = list(map(int, line.split()))
    if c < costs[s - 1][e - 1]:
        costs[s - 1][e - 1] = c

for d in range(n):
    for s in range(n):
        for e in range(n):
            if costs[s][e] > costs[s][d] + costs[d][e]:
                costs[s][e] = costs[s][d] + costs[d][e]

for s, d in enumerate(costs):
    for e, c in enumerate(d):
        if s == e or c == 1_000_000_000:
            print(0, end=" ")
        else:
            print(c, end=" ")
    print()