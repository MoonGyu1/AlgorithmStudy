import itertools, math

def primetest(n):
    if n < 2:
        return False
    for m in range(2, math.floor(math.sqrt(n)) + 1):
        if n % m == 0:
            return False
    return True

def solution(numbers):
    
    n_primes = 0
    primes = set()
    for r in range(1, len(numbers) + 1):
        for perm in itertools.permutations(numbers, r=r):
            n = int("".join(perm))       
            if primetest(n):
                primes.add(n)
        
    return len(primes)