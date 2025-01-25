import sys

inp = list(map(int, sys.stdin.read().split()))
N, M = inp[0], inp[1]
rms = inp[2:2 + N]
relations = list(zip(inp[2 + N::2], inp[2 + N + 1::2]))

W = [1 for _ in range(N)]

for i, j in relations:
    if rms[i - 1] >= rms[j - 1]:
        W[j - 1] = 0
    if rms[i - 1] <= rms[j - 1]:
        W[i - 1] = 0

print(sum(W))