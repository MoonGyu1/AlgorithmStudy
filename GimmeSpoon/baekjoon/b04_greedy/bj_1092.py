import sys, copy, heapq
inp = sys.stdin.readlines()

N = int(inp[0])
cranes = sorted(list(map(lambda x: (0, -int(x)), inp[1].split())))
M = int(inp[2])
boxes = sorted(list(map(lambda x: -int(x), inp[3].split())))

# Trivial Case
if cranes[0][1] > boxes[0]:
    print(-1)
else:

    heapq.heapify(cranes)

    maxt = 0

    for box in boxes:
        t, capa = heapq.heappop(cranes)
        if capa <= box:
            heapq.heappush(cranes, (t + 1, capa))
        else:
            passed = []
            while capa > box:
                passed.append((t, capa))
                t, capa = heapq.heappop(cranes)
            heapq.heappush(cranes, (t + 1, capa))
            for p in passed:
                heapq.heappush(cranes, p)
        if t + 1 > maxt:
            maxt = t + 1

    print(maxt)