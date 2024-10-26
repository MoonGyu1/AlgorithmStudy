import io, os, queue

inputs = io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()
N, M = int(inputs[0]), int(inputs[1])
times = sorted(list(map(int, inputs[2:])))

start, end = 1, 1_000_000_000_000_000_000
target = end // 2

def num_passed (max_time):
    max_passed = 0
    for time in times:
        max_passed += target // time
    return max_passed

while start < target and target < end:

    passed = num_passed(target)
    if passed < M:
        start = target
    else:
        end = target
    
    target = (start + end) // 2

if num_passed(start) < M:
    print(end)
else:
    print(start)