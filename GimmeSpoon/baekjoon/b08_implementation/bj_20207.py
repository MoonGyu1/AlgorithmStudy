import sys, heapq

inp = list(map(int, sys.stdin.read().split()))
schedules = sorted(
    list(zip(inp[1::2], inp[2::2])),
    key=lambda x: (x[0], -x[1]),
)

area = 0
fills = []
empty = list(range(1000))
heapq.heapify(empty)
first_s = last_e = schedules[0][0]
height = 0
for s, e in schedules:

    while len(fills) > 0 and fills[0][0] < s:
        e_, h = heapq.heappop(fills)
        heapq.heappush(empty, h)
        if e_ > last_e:
            last_e = e_

    if len(fills) == 0 and s > last_e + 1:
        area += (last_e - first_s + 1) * height
        first_s = s
        height = 0

    h = heapq.heappop(empty)
    heapq.heappush(fills, (e, h))
    if h + 1 > height:
        height = h + 1

while len(fills) > 0:
    e, _ = heapq.heappop(fills)
    if e > last_e:
        last_e = e
        
print(area + (last_e - first_s + 1) * height)