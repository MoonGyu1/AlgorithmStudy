import sys

inp = list(map(int, sys.stdin.read().split()))

n, q = inp[0], inp[1]
efficiencies = sorted(inp[2:2 + n])
targets = inp[2 + n:]

def binary_search (arr, key):
    start, center, end = 0, len(arr) // 2, len(arr) - 1

    while start < center < end:
        
        if arr[start] == key:
            return start
        if arr[center] == key:
            return center
        if arr[end] == key:
            return end
            
        if arr[center] > key:
            end = center
        else:
            start = center

        center = (end + start) // 2

    if arr[center] < key:
        return center, end
    else:
        return start, center

for target in targets:
    t = binary_search(efficiencies, target)

    if isinstance(t, tuple):
        print(0)
    else:
        print(t * (n - t - 1))