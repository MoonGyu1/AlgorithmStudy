import sys

inp = sys.stdin.read().split()
N = int(inp[0])
A = list(map(int, inp[1:N + 1]))
max_add, max_sub, max_mul, max_div = tuple(map(int, inp[-4:]))

min_res = 1000_000_000
max_res = -1000_000_000

def dfs(res, num_add = 0, num_sub = 0, num_mul = 0, num_div = 0):

    idx = num_add + num_sub + num_mul + num_div + 1

    if idx == N:
        global min_res, max_res
        
        if res < min_res:
            min_res = res
        if res > max_res:
            max_res = res
    
    if num_add < max_add:
        dfs(res + A[idx], num_add + 1, num_sub, num_mul, num_div)
    if num_sub < max_sub:
        dfs(res - A[idx], num_add, num_sub + 1, num_mul, num_div)
    if num_mul < max_mul:
        dfs(res * A[idx], num_add, num_sub, num_mul + 1, num_div)
    if num_div < max_div:
        if res < 0:
            dfs(-((-res) // A[idx]), num_add, num_sub, num_mul, num_div + 1)
        else:
            dfs(res // A[idx], num_add, num_sub, num_mul, num_div + 1)

dfs(A[0])

print(max_res)
print(min_res)