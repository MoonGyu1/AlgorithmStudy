import sys

inp = list(map(int, sys.stdin.read().split()))
N, K = inp[0], inp[1]

x_sorted = sorted(list(zip(inp[2::3], inp[3::3], inp[4::3])))
minarea = 4_000_000

for i, (x1, _, _) in enumerate(x_sorted):
    colors = set()
    for j, (x2, _, cx) in enumerate(x_sorted[i:]):
        colors.add(cx)
        if len(colors) == K:
            # Find area
            y_sorted = sorted(x_sorted[i: i + j + 1], key=lambda x: x[1])
            for _i, (_, y1, _) in enumerate(y_sorted):
                y_colors = set()
                for _, y2, cy in y_sorted[_i:]:
                    y_colors.add(cy)
                    if len(y_colors) == K:
                        area = abs((x2 - x1) * (y2 - y1))
                        if area < minarea:
                            minarea = area
                        break

print(minarea)