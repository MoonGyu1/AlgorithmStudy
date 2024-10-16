import sys, math, queue

def primetest(n):
    for p in range(2, math.floor(math.sqrt(n) + 1)):
        if n % p == 0:
            return False
    return True

def digits_to_number(dg):
    n = 0
    for d in dg:
        n = n * 10 + d
    return n

def every_palindrome(digit):

    palindromes = [0]

    for d in range((digit - 1) % 2, digit, 2):
        new_palindromes = []

        for v in range(10):
            for p in palindromes:
                if d == 0:
                    new_palindromes.append(p * 10 + (10 ** d) * v)
                else:
                    new_palindromes.append(p * 10 + (10 ** d) * v + v)

        palindromes = new_palindromes

    return palindromes

def get_digit(n):
    d = 0
    while n != 0:
        n = n // 10
        d += 1
    return d

def least_prime_palindrome (n):
    
    exp = get_digit(n)

    if n <= 2:
        print(2)
        return
    
    while True:
        for odd in (1, 3, 5, 7, 9):
            base = odd * (10 ** (exp - 1))
            if exp != 1:
                base += odd
            for palindrome in every_palindrome(exp - 2):
                p = base + palindrome * 10
                if p >= n and primetest(p):
                    print(p)
                    return
        exp += 1

N = int(sys.stdin.readline())

least_prime_palindrome(N)