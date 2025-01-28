import sys, queue

inp = sys.stdin.read().split()

N, K = int(inp[0]), int(inp[1])
line = inp[2]

q_part = queue.Queue()
q_robot = queue.Queue()

for i, l in enumerate(line):
    if l == "H":
        q_part.put(i)
    else:
        q_robot.put(i)

i, j = -11, -11
ret = 0

while not q_robot.empty():
    
    i = q_robot.get()
    while not q_part.empty() and j < i - K:
        j = q_part.get()

    if abs(i - j) <= K:
        ret += 1
        j = -11

print(ret)