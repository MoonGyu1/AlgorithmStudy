import io, os, sys

readlines = io.BytesIO(os.read(0, os.fstat(0).st_size)).readlines
go = list(map(lambda x: bytes.decode(x)[::2], readlines()))

for r, row in enumerate(go):
    for c, stone in enumerate(row):
        if stone != "0":
            # vertical
            if r <= 14 and len(set([go[r + i][c] for i in range(5)])) == 1:
                if (r == 0 or (r > 0 and go[r - 1][c] != stone)) and (r == 14 or (r < 14 and go[r + 5][c] != stone)):
                    print(stone)
                    print(r + 1, c + 1)
                    break
            # horizontal
            if c <= 14 and len(set([go[r][c + i] for i in range(5)])) == 1:
                if (c == 0 or (c > 0 and go[r][c - 1] != stone)) and (c == 14 or (c < 14 and go[r][c + 5] != stone)):
                    print(stone)
                    print(r + 1, c + 1)
                    break
            # upper-right diagonal
            if r >= 4 and c <= 14 and len(set([go[r - i][c + i] for i in range(5)])) == 1:
                if (r == 18 or c == 0 or (r < 18 and c > 0 and go[r + 1][c - 1] != stone)) and (r == 4 or c == 14 or (r > 4 and c < 14 and go[r - 5][c + 5] != stone)):
                    print(stone)
                    print(r + 1, c + 1)
                    break
            # upper-left diagonal
            if r <= 14 and c <= 14 and len(set([go[r + i][c + i] for i in range(5)])) == 1:
                if (r == 0 or c == 0 or (r > 0 and c > 0 and go[r - 1][c - 1] != stone)) and (r == 14 or c == 14 or (r < 14 and c < 14 and go[r + 5][c + 5] != stone)):
                    print(stone)
                    print(r + 1, c + 1)
                    break
    else:
        continue
    break
else:
    print(0)