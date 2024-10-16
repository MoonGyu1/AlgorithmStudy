import sys

readline = sys.stdin.readline
write = sys.stdout.write

exp = readline()
pipes = []

answer = 0
for c in exp[:-1]:
    if c == "(":
        if len(pipes) > 0:
            pipes[-1] += 1
        pipes.append(0)
    elif c == ")":
        if pipes[-1] > 0: #pipe
            pipes.pop()
            answer += 1
        else: #laser
            pipes.pop()
            answer += len(pipes)
    else:
        raise ValueError(f"{c} is not a valid character.")

print(answer)