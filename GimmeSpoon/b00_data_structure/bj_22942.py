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

# METHOD 2
# axis = [0] * 2_020_001
# for i, circle in enumerate(circles):
#     x1 = circle[0] - circle[1] + 1_000_000
#     x2 = circle[0] + circle[1] + 1_000_000
#     if axis[x1] != 0 or axis[x2] != 0:
#         print("NO")
#         break
#     else:
#         axis[x1] = i + 1
#         axis[x2] = -i - 1
# else:
#     stack = []
#     for c in axis:
#         if c == 0:
#             continue
#         if c > 0:
#             stack.append(c)
#         else:
#             if stack[-1] == -c:
#                 stack.pop()
#             else:
#                 print("NO")
#                 break
#     else:
#         print("YES")