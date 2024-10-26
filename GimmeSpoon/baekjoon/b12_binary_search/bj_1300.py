import sys
N, K = sys.stdin.read().split()
N, K = int(N), int(K)

def idx(k):
    order = 0
    for i in range(1, N + 1):
        order += max(0, min(k // i, N) * 2 - 2 * i + 1)
    return order

start, end = 1, 1_000_000_000
target = end // 2

while start < target and target < end:
    if idx(target) < K:
        start = target
    else:
        end = target
    target = (start + end) // 2

if idx(start) < K:
    print(end)
else:
    print(start)