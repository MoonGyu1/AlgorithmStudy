import sys, io, os

read = io.BytesIO(os.read(0, os.fstat(0).st_size)).read
write = sys.stdout.write

inp = read().split()
N = int(inp[0])
if N == 1:
    write(str(0))
else:
    energy = list(map(int, inp[1:2 * N - 1]))
    energy = list(zip(energy[::2], energy[1::2]))
    K = int(inp[2 * N - 1])

    if N == 2:
        write(str(energy[0][0]))
    else:
        table = [[0, 1000_000], [energy[0][0], 1000_000], [min(energy[0][1], energy[0][0] + energy[1][0]), 1000_000]]
        for i in range(3, N):
            table[i - 2][0] = min(table[i - 2][0], table[i - 1][0] + energy[i - 1][0])
            table[i - 2][1] = min(table[i - 2][1], table[i - 1][1] + energy[i - 1][0])
            table[i - 3][0] = min(table[i - 3][0], table[i - 1][0] + energy[i - 1][1], table[i - 2][0] + energy[i - 2][0])
            table[i - 3][1] = min(table[i - 3][1], table[i - 1][1] + energy[i - 1][1], table[i - 2][1] + energy[i - 2][0])

            table.append([min(table[i - 2][0] + energy[i - 2][1], table[i - 1][0] + energy[i - 1][0]),
                          min(table[i - 3][0] + K, table[i - 2][1] + energy[i - 2][1], table[i - 1][1] + energy[i - 1][0])])

        print(table)
        write(str(min(table[-1])))
        