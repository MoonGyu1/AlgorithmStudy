import sys

readline = sys.stdin.readline
write = sys.stdout.write

N = int(readline())
circles = [None] * N

for i, _ in enumerate(circles):
    circles[i] = list(map(int, readline().split()))

# METHOD 1
points = []
for i, circle in enumerate(circles):
    points.append((circle[0] - circle[1], i+1))
    points.append((circle[0] + circle[1], -i-1))

points = sorted(points, key=lambda x: x[0])
stack = []
for point in points:
    if point[1] < 0:
        if stack[-1] == -point[1]:
            stack.pop()
        else:
            print("NO")
            break
    else:
        stack.append(point[1])
else:
    print("YES")