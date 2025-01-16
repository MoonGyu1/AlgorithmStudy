import sys, queue

s1 = sys.stdin.readline().strip()
s2 = sys.stdin.readline().strip()

table = [[0 for _ in range(len(s2) + 1)] for _ in range(len(s1) + 1)]

for i1, c1 in enumerate(s1):
    i = i1 + 1
    for i2, c2 in enumerate(s2):
        j = i2 + 1
        if c1 == c2:
            table[i][j] = table[i - 1][j - 1] + 1
        else:
            table[i][j] = max(table[i - 1][j], table[i][j - 1])

print(table[-1][-1])