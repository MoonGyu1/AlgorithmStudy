import sys, itertools, collections

N, integers, num_ops = sys.stdin.readlines()
N = int(N)
if N == 1:
    print(integers)
else:
    integers = list(map(int, integers.split()))
    num_plus, num_mult = list(map(int, num_ops.split()))

    if N == 2:
        if num_plus == 1:
            print(integers[0] + integers[1])
        else:
            print(integers[0] * integers[1])
    else:
        results = {i : collections.defaultdict(int) for i in range(1, N + 1)}
        for i, v in enumerate(integers):
            idx = [0 for _ in range(N)]
            idx[i] = 1
            results[1][(*idx, 0, 0)] = v

        for i in range(2, N + 1):
            for j in range(1, i // 2 + 1):
                res1, res2 = results[j], results[i - j]
                for k1, v1 in res1.items():
                    for k2, v2 in res2.items():

                        new_plus, new_mult = k1[-2] + k2[-2], k1[-1] + k2[-1]
                        if new_plus > num_plus or new_mult > num_mult:
                            continue

                        new_idx = []
                        for inc1, inc2 in zip(k1[:-2], k2[:-2]):
                            if inc1 and inc2:
                                break
                            elif inc1 or inc2:
                                new_idx.append(1)
                            else:
                                new_idx.append(0)
                        else:
                            if new_plus < num_plus:
                                new_key = (*new_idx, new_plus + 1, new_mult)
                                new_val = v1 + v2
                                if new_val > results[i][new_key]:
                                    results[i][new_key] = new_val
                            if new_mult < num_mult:
                                new_key = (*new_idx, new_plus, new_mult + 1)
                                new_val = v1 * v2
                                if new_val > results[i][new_key]:
                                    results[i][new_key] = new_val

        print(next(iter(results[N].values())))