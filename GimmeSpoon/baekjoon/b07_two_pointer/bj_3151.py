import sys, collections

inp = list(map(int, sys.stdin.read().split()))
N = inp[0]
CMB2 = lambda x: x * (x - 1) // 2

if N < 3:
    print(0)
else:
    caps = inp[1:]

    neg = collections.Counter()
    pos = collections.Counter()
    nz = 0
    n_avt = 0

    for cap in caps:
        if cap > 0:
            pos[cap] += 1
        elif cap < 0:
            neg[cap] += 1
        else:
            nz += 1

    for p in sorted(pos.keys()):

        if -p in neg:
            n_avt += nz * pos[p] * neg[-p]
            
        for n in sorted(neg.keys(), reverse=True):
            cp = -(p + n)
            if cp > 0 and p <= cp and cp in pos:
                if p == cp:
                    n_avt += neg[n] * CMB2(pos[p])
                else:
                    n_avt += pos[p] * pos[cp] * neg[n]
            elif cp < 0 and n <= cp and cp in neg:
                if n == cp:
                    n_avt += pos[p] * CMB2(neg[n])
                else:
                    n_avt += pos[p] * neg[n] * neg[cp]

    print(n_avt + nz * (nz - 1) * (nz - 2) // 6)