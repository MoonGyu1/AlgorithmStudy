import sys

N = int(sys.stdin.readline())
minemap, openmap = sys.stdin.readlines(N * N), sys.stdin.readlines(N * N)
directions = ((-1, 0), (-1, 1), (0, 1), (1, 1),
              (1, 0), (1, -1), (0, -1), (-1, -1))

lost = False
for r1, r2 in zip(minemap, openmap):
    for m, o in zip(r1, r2):
        if m == "*" and o == "x":
            lost = True

for r in range(N):
    for c in range(N):
        if lost and minemap[r][c] == "*":
            sys.stdout.write("*")
        elif openmap[r][c] == "x":
            mines = 0
            for rd, cd in directions:
                rn, cn = r + rd, c + cd
                if 0 <= rn < N and 0 <= cn < N and minemap[rn][cn] == "*":
                    mines += 1
            sys.stdout.write(str(mines))
        else:
            sys.stdout.write(".")
    sys.stdout.write("\n")