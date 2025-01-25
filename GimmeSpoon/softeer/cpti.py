import sys

inp = sys.stdin.read().split()
N, M = int(inp[0]), int(inp[1])
personalities = inp[2:]

n_friendlies = 0
for i, p1 in enumerate(personalities[:-1]):
    for p2 in personalities[i + 1:]:
        d = 0
        for c1, c2 in zip(p1, p2):
            if c1 != c2:
                d += 1
            if d > 2:
                break
        else:
            n_friendlies += 1
            
print(n_friendlies)