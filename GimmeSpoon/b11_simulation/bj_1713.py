import sys, heapq

num_frames = int(sys.stdin.readline())
num_recoms = int(sys.stdin.readline())
recoms = list(map(int, sys.stdin.readline().split()))

len_frames = 0
frames = []
st_rec = [0 for _ in range(101)]
st_ins = [0 for _ in range(101)]

for t, st in enumerate(recoms):
    if st_rec[st] == 0: # new
        if len_frames == num_frames:
            dropped = heapq.heappop(frames)
            while st_rec[dropped[2]] != dropped[0] or st_ins[dropped[2]] != dropped[1]:
                dropped = heapq.heappop(frames)
            st_rec[dropped[2]] = 0
        else:
            len_frames += 1
        st_rec[st] += 1
        st_ins[st] = t
        heapq.heappush(frames, (st_rec[st], t, st))
    else:
        st_rec[st] += 1
        heapq.heappush(frames, (st_rec[st], st_ins[st], st))

while len_frames > 0:
    rec, t, st = frames.pop()
    if st_rec[st] == rec and st_ins[st] == t:
        st_rec[st] = -1
        len_frames -= 1

for i, v in enumerate(st_rec):
    if v == -1:
        print(i, end=" ")