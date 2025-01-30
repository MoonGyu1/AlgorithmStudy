import sys

N, M = list(map(int, sys.stdin.readline().split()))
personalities = [int(sys.stdin.readline(), 2) for _ in range(N)]

if N == 1:
    print(0)
elif M < 3:
    print(N * (N - 1) // 2)
else:
    MASK = []
    for i in range(M):
        a = 1 << i
        MASK.append(a)
        for j in range(i + 1, M):
            MASK.append(a | (1 << j))

    relationships = {}

    for personality in personalities:
        if personality in relationships:
            relationships[personality]["num"] += 1
        else:
            relationships[personality] = {
                "num" : 1,
                "family" : set([personality ^ mask for mask in MASK]),
            }

    a, b = 0, 0
    for v in relationships.values():
        a += v["num"] * (v["num"] - 1) // 2
        for neighbor in v["family"]:
            if neighbor in relationships:
                b += v["num"] * relationships[neighbor]["num"]

    print(a + b // 2)