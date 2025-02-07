import sys

inp = sys.stdin.readlines()
H, K, R = list(map(int, inp[0].split()))
total_tasks = list(map(lambda x: list(map(int, x.split())), inp[1:]))

if H >= R:
    print(0)
elif R > H + K * 2 ** H:
    print(sum(list(map(lambda x: sum(x), total_tasks))))
else:
    indexes = {
        1 : [1, 0]
    }

    for h in range(2, H + 1):
        indexes[h] = []
        for idx in indexes[h - 1]:
            for i in [(0, 1), (1, 0)][h % 2]:
                indexes[h].append((i << (h - 1)) + idx)

    r = 0
    i = 0
    num_staffs = 2 ** H
    ret = 0
    while r < R - H:
        ret += total_tasks[indexes[H][r % num_staffs]][i]
        r += 1
        i = r // num_staffs

    print(ret)