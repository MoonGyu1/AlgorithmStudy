import io, os, queue

inp = io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()
N, C = int(inp[0]), int(inp[1])
homes = sorted(list(map(int, inp[2:])))

def binary_search (end):

    def validate(interval):
        threshold = 0
        max_installations = 0
        for home in homes:
            if home >= threshold:
                max_installations += 1
                threshold = home + interval
        return max_installations

    start = 1
    target = end // 2

    while start < target and target < end:
        
        if validate(target) < C:
            end = target
        else:
            start = target

        target = (start + end) // 2

    if validate(end) < C:
        return start
    else:
        return end

print(binary_search(homes[-1]))