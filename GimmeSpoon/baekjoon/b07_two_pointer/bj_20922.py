import sys, collections

inp = list(map(int, sys.stdin.read().split()))

N, K = inp[0], inp[1]
seq = inp[2:]

# counts = collections.Counter(seq)
counts = collections.defaultdict(
    lambda: {"num" : int(), "indexes" : list()})
exceeds = []

for i, v in enumerate(seq):
    counts[v]["num"] += 1
    counts[v]["indexes"].append(i)
    if counts[v]["num"] > K:
        exceeds.append(v)

i, j = 0, N - 1

while len(exceeds) > 0:
    i

print(j - i + 1)