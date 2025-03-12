import sys

inp = list(map(int, sys.stdin.read().split()))
N, M = inp[:2]

prerequisites = {n : [] for n in range(1, N + 1)}

for a, b in list(zip(inp[2::2], inp[3::2])):
    prerequisites[b].append(a)

min_semester = {n : 0 for n in range(1, N + 1)}

def min_sem_dfs(req:dict, course:int):

    if min_semester[course] > 0:
        return min_semester[course]
    
    if len(req[course]) == 0:
        return 1
    
    for pre in req[course]:
        if min_semester[pre] != 0:
            min_semester[course] = max(min_semester[pre] + 1, min_semester[course])
        else:
            min_semester[course] = max(min_sem_dfs(req, pre) + 1, min_semester[course])

    return min_semester[course]

for c in range(1, N + 1):
    print(min_sem_dfs(prerequisites, c), end=" ")