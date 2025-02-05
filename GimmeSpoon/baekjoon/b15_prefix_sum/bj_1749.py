import sys

inp = list(map(int, sys.stdin.read().split()))
R, C = inp[0], inp[1]
matrix = inp[2:]

acc_table = [[0 for _ in range(C)] for _ in range(R)]
for i, v in enumerate(matrix):
    r, c = i // C, i % C
    acc_table[r][c] = v
    if r > 0:
        acc_table[r][c] += acc_table[r - 1][c]
    if c > 0:
        acc_table[r][c] += acc_table[r][c - 1]
        if r > 0:
            acc_table[r][c] -= acc_table[r - 1][c - 1]
    
maxsum = -400_000_000
for r2, row in enumerate(acc_table):
    for c2, acc in enumerate(row):
        for r1 in range(r2 + 1):
            for c1 in range(c2 + 1):
                v = acc_table[r2][c2]
                if r1 > 0:
                    v -= acc_table[r1 - 1][c2]
                if c1 > 0:
                    v -= acc_table[r2][c1 - 1]
                    if r1 > 0:
                        v += acc_table[r1 - 1][c1 - 1]
                if v > maxsum:
                    maxsum = v

print(maxsum)
