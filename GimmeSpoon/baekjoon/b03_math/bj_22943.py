import sys, itertools, math

K, M = list(map(int, sys.stdin.readline().split()))

def prime_test(n):
    if n <= 1:
        return False
    for m in range(2, math.ceil(math.sqrt(n)) + 1):
        if n % m == 0:
            return False
    return True

cnt = 0
for msn in range(1, 10):
    perms = [n for n in range(0, 10) if n != msn]
    for restn in itertools.permutations(perms, r = K - 1):

        num = [msn]
        num += restn
        num = int("".join(list(map(str, num))))

        if num & 1: # odd
            if not prime_test(num - 2):
                continue
        else: # even
            for n in range(3, num // 2 + 1):
                if n != num - n and prime_test(n) and prime_test(num - n):
                    break
            else:
                continue

        n = num
        while n % M == 0:
            n = n // M
        
        if n & 1: # odd
            for m in range(3, math.ceil(math.sqrt(n)) + 1):
                if n % m == 0 and prime_test(m) and prime_test(n // m):
                    cnt += 1
        else: # even
            if prime_test(n // 2):
                cnt += 1

print(cnt)