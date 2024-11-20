import sys, copy, heapq
inp = sys.stdin.readlines()

N = int(inp[0])
cranes = list(map(lambda x: -int(x), inp[1].split()))
M = int(inp[2])
boxes = sorted(list(map(int, inp[3].split())))

t = 1
boxes = reversed(boxes)
heapq.heapify(cranes)

while True:

    leftover = []
    capas = copy.deepcopy(cranes)

    for box in boxes:
        if len(capas) == 0:
            leftover.append(box)
            continue
        capa = -heapq.heappop(capas)
        if capa < box:
            leftover.append(box)
            heapq.heappush(capas, -capa)

    if len(leftover) == 0:
        print(t)
        break
    elif len(capas) > 0 and capas[0] == cranes[0]:
        print(-1)
        break

    boxes = leftover
    t += 1