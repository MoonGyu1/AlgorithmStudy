import sys, itertools

inp = list(map(int, sys.stdin.read().split()))

N, X = inp[0], inp[1]
visits = inp[2:]

if X == 1:
    max_visits = max(visits)
else:
    acc = list(itertools.accumulate(visits, initial = 0))
    max_visits = 0
    max_counts = 1

    for i, v in enumerate(acc[:-X]):
        visits_for_interval = acc[i + X] - acc[i]
        if visits_for_interval > max_visits:
            max_visits = visits_for_interval
            max_counts = 1
        elif visits_for_interval == max_visits:
            max_counts += 1

if max_visits == 0:
    print("SAD")
else:
    print(max_visits)
    print(max_counts)