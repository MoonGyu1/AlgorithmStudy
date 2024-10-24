import io, os, queue

inputs = io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()
N, M = int(inputs[0]), int(inputs[1])
stimes = list(map(int, inputs[2:]))

q = queue.PriorityQueue()
for i, t in enumerate(stimes):
    et = [0 for _ in range(N)]
    et[i] = t
    q.put((t, -1, et))

while True:

    t, m, ets = q.get()

    print(t, -m, ets)

    if m == -M:
        print(t)
        break

    for i, t in enumerate(stimes):
        et = copy.deepcopy(ets)
        ets[i] += t
        if ets[i] > t:
            t = ets[i]
        q.put((t, m - 1, ets))