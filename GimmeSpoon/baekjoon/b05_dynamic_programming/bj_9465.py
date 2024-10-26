import sys

readline = sys.stdin.readline
write = sys.stdout.write

T = int(readline())

for t in range(T):
    n = int(readline())
    r1 = list(map(int, readline().split()))
    r2 = list(map(int, readline().split()))
    if n == 1:
        print(max(r1[0], r2[0]))
        continue
    max_scores = [(r1[0], r2[0]), (r1[1] + r2[0], r2[1] + r1[0])]
    for c in zip(r1[2:], r2[2:]):
        max_scores.append((max(max_scores[-1][1], max_scores[-2][1]) + c[0],
                           max(max_scores[-1][0], max_scores[-2][0]) + c[1]))
    print(max(max_scores[-1]))