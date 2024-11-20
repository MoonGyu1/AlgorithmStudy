import sys, collections

inp = sys.stdin.readline()
N = int(inp[0])
inp = sys.stdin.readlines()

points = collections.defaultdict(int)

for line in inp:
    start, end = tuple(map(int, line.split()))
    points[start] += 1
    points[end] -= 1

curmq, maxmq = 0, 0
maxbg, maxed, mf = None, None, False
for t in sorted(list(points.keys())):
    curmq += points[t]
    if maxmq < curmq:
        maxmq = curmq
        maxbg = t
        mf = True
    elif mf and curmq < maxmq:
        maxed = t
        mf = False

print(maxmq)
print(maxbg, maxed)