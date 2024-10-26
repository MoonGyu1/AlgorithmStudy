import io, os

inp = io.BytesIO(os.read(0, os.fstat(0).st_size)).readlines()
N = int(inp[0])

output = []
for pair in inp[1:]:
    p1, p2 = pair.decode().split()
    output.append(p2[p1.upper().index("X")].upper())
print("".join(output))