import sys, math, queue

def primetest(n):
    for p in range(2, math.floor(math.sqrt(n) + 1)):
        if n % p == 0:
            return True
    return False

def digits_to_number(dg):
    n = 0
    for d in dg:
        n = n * 10 + d
    return n

def d_palindromes(digit):

    q1, q2 = [0], []

    for _ in range(digit):

        while len(q1):
            q = q1.pop()
            for d in range(0, 10):
                q2.append(q * 10 + d)
                
        temp = q1
        q1 = q2
        q2 = temp

    if len(q1):
        return q1
    else:
        return q2

def main (n):
    
    exp = 0

    while True:
        
        for odd in (1, 3, 5, 7, 9):
            base = odd * (10 ** exp) + odd
            for palindrome in d_palindromes(exp - 1):
                p = base + palindrome * 10 ** (exp - 1)
                if p > n and primetest(palindrome):
                    print(p)
                    return
        exp += 1

N = int(sys.stdin.readline())

main(N)