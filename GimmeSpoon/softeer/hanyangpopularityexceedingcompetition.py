import sys
inp = list(map(int, (sys.stdin.read().split())))
N = inp[0]
x = 0
for p, c in zip(inp[1::2], inp[2::2]):
    if abs(p - x) <= c:
        x += 1
print(x)