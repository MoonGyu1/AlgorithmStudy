import io, os

K, P, N = list(map(int, io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()))

k = K
M = 1000000007

if K > M:
    k %= M

for _ in range(N):
    k = P * k % 1000000007

print(k)