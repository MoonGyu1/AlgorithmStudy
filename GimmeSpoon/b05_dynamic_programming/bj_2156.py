import sys

readline = sys.stdin.readline
write = sys.stdout.write

n = int(readline())
wine = int(readline())
if n == 1:
    print(wine)
else:
    table = [(0, wine)]
    wine = int(readline())
    last_wine = wine
    table.append((table[0][1], table[0][1] + wine))
    for i in range(2, n):
        wine = int(readline())
        table.append((max(table[i - 1]), max(table[i - 2][0] + last_wine + wine, table[i - 2][1] + wine)))
        last_wine = wine
    print(max(table[-1]))
