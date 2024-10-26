import io, os

inp = io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()
N, C = int(inp[0]), int(inp[1])
homes = sorted(list(map(int, inp[2:])))
inp = None

interval = (homes[-1] - homes[0]) / (C - 1)

def search(val):
    start, end = 0, len(homes) - 1
    pivot = len(homes) // 2

    while start < pivot and pivot < end:

        if homes[start] == val:
            return (val, val)
        if homes[end] == val:
            return (val, val)
        if homes[pivot] == val:
            return (val, val)
        
        if homes[pivot] > val:
            end = pivot
        else:
            start = pivot

        pivot = (end + start) // 2
    
    if homes[pivot] < val:
        return (homes[pivot], homes[pivot + 1])
    else:
        return (homes[pivot - 1], homes[pivot])

target = homes[0]
for i in range(C - 2):
    target += interval
    l, r = search(target)
    l