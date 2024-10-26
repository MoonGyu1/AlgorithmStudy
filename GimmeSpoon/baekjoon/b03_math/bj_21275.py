import sys

orda = ord('a')

def c_to_d10(n, d):
    ordn = ord(n)
    if ordn >= orda:
        n = ordn - orda + 10
    else:
        n = int(n)

    if n >= d:
        return None
    else:
        return n
    
def dx_to_d10(n, d):

    n10 = 0
    digit = 1
    for c in reversed(n):
        c10 = c_to_d10(c, d)
        if c10 is None:
            return None
        n10 += digit * c10
        digit *= d
    
    if n10 < 9.223372e+18:
        return n10
    else:
        return None

na, nb = sys.stdin.readline().split()
x, a, b = None, None, None

for da in range(2, 37):
    a10 = dx_to_d10(na, da)
    if a10 is None:
        continue
    for db in range(2, 37):
        if da == db:
            continue
        b10 = dx_to_d10(nb, db)
        if b10 is None:
            continue
        if a10 == b10:
            if x is not None:
                sys.stdout.write("Multiple")
                exit(0)
            x = a10
            a = da
            b = db

if x is None:
    sys.stdout.write("Impossible")
else:
    sys.stdout.write(" ".join(map(str, [x, a, b])))