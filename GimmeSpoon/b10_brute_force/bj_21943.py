import sys, itertools, queue

N, integers, num_ops = sys.stdin.readlines()
N = int(N)
integers = list(map(int, integers.split()))
num_plus, num_mult = list(map(int, num_ops.split()))
num_ops = num_plus + num_mult

results = {i : {j : [] for j in range(2, N + 1)} for i in range(N)}

for layer in range(2, N + 1):

    results[layer].append(())