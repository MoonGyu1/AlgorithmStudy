import sys, heapq, datetime

inp = sys.stdin.readline()
for _ in range(int(inp)):
    n, cln_time = list(map(int, sys.stdin.readline().split()))
    cln_time = datetime.timedelta(minutes = cln_time)
    heap = []
    history = []
    for ofs in range(n):
        _, chin_d, chin_t, chout_d, chout_t = sys.stdin.readline().split()
        chin = datetime.datetime.fromisoformat(chin_d + " " + chin_t)
        chout = datetime.datetime.fromisoformat(chout_d + " " + chout_t)
        history.append((chin, chout))
    for chin, chout in sorted(history):
        if len(heap) > 0:
            if heap[0] + cln_time <= chin:
                heapq.heappop(heap)
        heapq.heappush(heap, chout)
    print(len(heap))
 
