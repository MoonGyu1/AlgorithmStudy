import sys, heapq

def findValid (heap, deleted):
    v, i = heapq.heappop(heap)
    while deleted[i]:
        v, i = heapq.heappop(heap)
    return v, i

t = int(sys.stdin.readline())
for _ in range(t):
    k = int(sys.stdin.readline())
    maxh, minh = [], []
    l = 0
    index = 0
    deleted = []
    for _ in range(k):
        op = sys.stdin.readline()
        if op[0] == "I": #INSERT
            v = int(op.split()[1])
            l += 1
            heapq.heappush(maxh, (-v, index))
            heapq.heappush(minh, (v, index))
            deleted.append(False)
            index += 1
        else:
            if l > 0:
                if op[2] == "1": #DELETE MAX
                    _, i = findValid(maxh, deleted)
                    deleted[i] = True
                else: # DELETE MIN
                    _, i = findValid(minh, deleted)
                    deleted[i] = True
                l -= 1
    if l > 0:
        print(-findValid(maxh, deleted)[0], findValid(minh, deleted)[0])
    else:
        print("EMPTY")