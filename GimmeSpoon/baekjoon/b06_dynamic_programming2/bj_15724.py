import sys

inp = sys.stdin.read().split()
N, M = int(inp[0]), int(inp[1])
population = list(map(int, inp[2:2+N*M]))
K = int(inp[2 + N * M])
queries = list(map(int, inp[3 + N * M:]))

acc_table = [[0 for _ in range(M)] for _ in range(N)]
for r, row in enumerate(acc_table):
    for c, _ in enumerate(row):
        acc_table[r][c] = population[r * M + c]
        if r > 0:
            acc_table[r][c] += acc_table[r - 1][c]
            if c > 0:
                acc_table[r][c] += acc_table[r][c - 1]\
                    - acc_table[r - 1][c - 1]
        elif c > 0:
            acc_table[r][c] += acc_table[r][c - 1]

for k in range(K):
    qn = 4 * k
    x1, y1, x2, y2 = queries[qn], queries[qn + 1],\
        queries[qn + 2], queries[qn + 3]
    
    res = acc_table[x2 - 1][y2 - 1]
    if x1 > 1:
        res -= acc_table[x1 - 2][y2 - 1]
        if y1 > 1:
            res += acc_table[x1 - 2][y1 - 2] - acc_table[x2 - 1][y1 - 2]
    elif y1 > 1:
        res -= acc_table[x2 - 1][y1 - 2]
    
    print(res)