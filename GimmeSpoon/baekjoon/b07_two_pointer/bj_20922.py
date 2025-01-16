import sys, copy, queue

inp = list(map(int, sys.stdin.read().split()))
N, K = inp[0], inp[1]
arr = inp[2:]
values = {}
k_exceeds = []

for i, v in enumerate(arr):
    if v not in values:
        values[v] = {
            "count" : 1,
            "indexes" : [i]
        }
    else:
        values[v]["count"] += 1
        values[v]["indexes"].append(i)
        if values[v]["count"] > K:
            k_exceeds.append(v)

if len(k_exceeds) == 0:
    print(N)
else:
    maxlen = 0
    for i in range(0, N):
        for j in range(N - 1, -1, -1):
            for v in k_exceeds:
                e = values[v]["count"] - K
                for idx in values[v]["indexes"]:
                    if idx < i or idx > j:
                        e -= 1
                    if e == 0:
                        if j - i + 1 > maxlen:
                            maxlen = j - i + 1
                        break
                else:
                    continue
    print(maxlen)