import sys, itertools

inp = sys.stdin.readlines()

N = int(inp[0])
difficulties = list(map(int, inp[1].split()))

Q = int(inp[2])
queries = list(map(lambda x: tuple(map(int, x.split())), inp[3:3+Q]))

mistakes = [0] + [1 if d > difficulties[i + 1] else 0 for i, d in enumerate(difficulties[:-1])]
mistakes = list(itertools.accumulate(mistakes))

for x, y in queries:
    if x == y:
        print(0)
    else:
        print(mistakes[y - 1] - mistakes[x - 1])