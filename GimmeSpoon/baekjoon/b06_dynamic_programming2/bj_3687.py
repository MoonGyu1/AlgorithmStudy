import sys

matches_for_digit = [6, 2, 5, 5, 4, 5, 6, 3, 7, 6]

tcs = list(map(int, sys.stdin.read().split()[1:]))

minvalues = [0, 0]
maxvalues = [0, 0]

for n in tcs:

    while n >= len(minvalues):

        t = len(minvalues)
        minvalues.append(None)
        maxvalues.append(None)

        for v, m in enumerate(matches_for_digit):
            if t == m and v != 0:
                if minvalues[t] is None or v < minvalues[t]:
                    minvalues[t] = v
                if maxvalues[t] is None or  v > maxvalues[t]:
                    maxvalues[t] = v
                break
            elif t - m >= 2:
                if minvalues[t] is None or minvalues[t] > v + 10 * minvalues[t - m]:
                    minvalues[t] = v + minvalues[t - m] * 10
                if maxvalues[t] is None or maxvalues[t] < v + 10 * maxvalues[t - m]:
                    maxvalues[t] = v + maxvalues[t - m] * 10

    print(minvalues[n], maxvalues[n])