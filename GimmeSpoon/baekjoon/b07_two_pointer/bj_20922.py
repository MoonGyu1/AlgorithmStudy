import sys, collections

inp = list(map(int, sys.stdin.read().split()))

N, K = inp[0], inp[1]
seq = inp[2:]

if N == 1:
    print(1)
else:
    lls = 1
    i, j = 0, 1
    counter = collections.Counter([seq[0]])
    for v in seq[1:]:
        counter[v] += 1
        while counter[v] > K:
            counter[seq[i]] -= 1
            i += 1
        j += 1
        if j - i > lls:
            lls = j - i
    print(lls)