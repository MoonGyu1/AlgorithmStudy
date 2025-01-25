import sys

inp = list(map(int, sys.stdin.read().split()))
N = inp[0]
durations = list(zip(inp[1::4], inp[2::4]))
transports = list(zip(inp[3::4], inp[4::4]))

totaltime = 0
table = [[durations[0][0], durations[0][1]]]

for i, (a, b) in enumerate(durations[1:]):
    atb, bta = transports[i][0], transports[i][1]
    table.append([
        min(table[i][0] + a, table[i][1] + bta + a),
        min(table[i][0] + atb + b, table[i][1] + b),
    ])

print(min(table[-1]))