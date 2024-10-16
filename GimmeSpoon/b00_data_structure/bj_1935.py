import sys

OPS = ('*', '+', '/', '-')

def operate(a, b, o):
    if o == "*":
        return a * b
    elif o == "+":
        return a + b
    elif o == "/":
        return a / b
    elif o == "-":
        return a - b
    else:
        raise ValueError("Invalid expression.")

readline = sys.stdin.readline
write = sys.stdout.write

N = int(readline())
operands = [0] * N
exp = readline()

for i, _ in enumerate(operands):
    operands[i] = int(readline())

ops = []
for c in reversed(exp[:-1]):
    if c in OPS:
        ops.append(([], c))
    else:
        ops[-1][0].append(operands[ord(c) - ord("A")])
        while len(ops[-1][0]) == 2:
            op = ops.pop()
            res = operate(op[0][1], op[0][0], op[1])
            if len(ops) == 0:
                write(f"{res:.2f}")
                break
            else:
                ops[-1][0].append(res)
    