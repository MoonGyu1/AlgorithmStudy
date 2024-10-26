import sys, datetime, collections, heapq

readline = sys.stdin.readline

N, L, F = readline().split()
N, F = int(N), int(F)
L = datetime.timedelta(days=int(L[:3]), hours=int(L[4:6]), minutes=int(L[7:]))

record = collections.defaultdict(dict)
deliquents = []
fine = collections.defaultdict(int)
for _ in range(N):
    line = readline()
    timestamp, part, name = datetime.datetime.fromisoformat(line[:16]), *line[16:].split()
    if part in record[name]:
        deadline = record[name][part] + L
        if deadline < timestamp:
            overdue = timestamp - deadline
            if name not in fine:
                heapq.heappush(deliquents, name)
            fine[name] += (overdue.days * 1440 + overdue.seconds // 60) * F
        del record[name][part]
    else:
        record[name][part] = timestamp

if len(deliquents):
    while len(deliquents):
        deliquent = heapq.heappop(deliquents)
        print(deliquent, fine[deliquent])
else:
    print(-1)