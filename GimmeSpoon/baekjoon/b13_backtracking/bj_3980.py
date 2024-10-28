import sys

C = int(sys.stdin.readline())

def dfs(sum_stat, selected, stats):

    if len(stats) == 0:
        return sum_stat
    
    position = stats[0]

    best = 0
    for i, stat in enumerate(position):
        if stat > 0 and selected[i] != 1:
            if (len(stats) - 1) * 100 + stat + sum_stat < best:
                continue
            selected[i] = 1
            comb = dfs(sum_stat + stat, selected, stats[1:])
            selected[i] = 0
            if comb > best:
                best = comb

    return best

for _ in range(C):
    p1 = list(map(int, sys.stdin.readline().split()))
    p2 = list(map(int, sys.stdin.readline().split()))
    p3 = list(map(int, sys.stdin.readline().split()))
    p4 = list(map(int, sys.stdin.readline().split()))
    p5 = list(map(int, sys.stdin.readline().split()))
    p6 = list(map(int, sys.stdin.readline().split()))
    p7 = list(map(int, sys.stdin.readline().split()))
    p8 = list(map(int, sys.stdin.readline().split()))
    p9 = list(map(int, sys.stdin.readline().split()))
    p10 = list(map(int, sys.stdin.readline().split()))
    p11 = list(map(int, sys.stdin.readline().split()))

    candidates = list(zip(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11))

    print(dfs(0, [0 for _ in range(11)], candidates))
