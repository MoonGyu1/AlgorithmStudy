import sys, collections

n = int(sys.stdin.readline())
ii = collections.defaultdict(int)
for r in map(int, sys.stdin.readline().split()):
    for i in range(2, r + 1):
        if r % i == 0:
            ii[i] += 1

print(max(list(ii.values())))