import sys

N, r, c = list(map(int, sys.stdin.readline().split()))

halfn = 2 ** (N - 1)
ulr, ulc, ulv = 0, 0, 0

while halfn > 1:
    if r < ulr + halfn:
        if c >= ulc + halfn:
            ulv = ulv + halfn * halfn
            ulc = ulc + halfn
    else:
        if c < ulc + halfn:
            ulv = ulv + 2 * halfn * halfn
            ulr = ulr + halfn
        else:
            ulv = ulv + 3 * halfn * halfn
            ulr = ulr + halfn
            ulc = ulc + halfn

    halfn = halfn // 2

print(ulv + (r - ulr) * 2 + (c - ulc))