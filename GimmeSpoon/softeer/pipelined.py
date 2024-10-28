import sys
N = int(sys.stdin.readline())
stages = list(map(int, sys.stdin.readline().split()))
stages = sorted(stages)
print(len(stages) - 1 + stages[-1])