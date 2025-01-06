import sys, queue

inp = sys.stdin.read().split()

N, K = int(inp[0]), int(inp[1])
coords = sorted(list(map(int, inp[2:])))
intervals = sorted([(coord - coords[i], i, i + 1) \
         for i, coord in enumerate(coords[1:])])

if N <= K: # trivial case
    print(0)
else:
    
    sensors = set()
    n_coverages = 0
    total_coverage = 0
    included = [False for _ in range(len(intervals))]

    for i, (dist, left, right) in enumerate(intervals): # From large

        if left in sensors:
            if right in sensors:
                n_coverages -= 1
        elif right not in sensors:
            n_coverages += 1

        sensors.add(left)
        sensors.add(right)
        total_coverage += dist
        included[i] = True

        if len(sensors) + K - n_coverages == N:
            break

    if n_coverages > K:
        temp = -1
        uncovered = queue.PriorityQueue()
        for covered, (dist, left, right) in zip(included, intervals):
            if covered:
                if temp >= 0:
                    uncovered.put(temp)
                temp = -1
            else:
                if temp == -1:
                    temp = 0
                temp += dist

        for _ in range(n_coverages - K):
            total_coverage += uncovered.get()

    print(total_coverage)