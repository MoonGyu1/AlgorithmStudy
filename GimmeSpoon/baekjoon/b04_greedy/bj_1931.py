import sys, queue

inp =  sys.stdin.readlines()
N = int(inp[0])

q = queue.PriorityQueue()
for line in inp[1:]:
    start, end = list(map(int, line.split()))
    q.put((end, start))

cnt = 0
last = 0

while not q.empty():
    end, start = q.get()

    if start >= last:
        cnt += 1
        last = end

print(cnt)