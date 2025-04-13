import collections, itertools

def solution(N, number):
    
    num_digits = collections.defaultdict(list)
    num_digits[0] = [0]
    for r in range(1, 9):
        num_digits[r] = [N * 10 ** (r - 1) + num_digits[r - 1][0]]
        if num_digits[r][0] == number:
            return r
    searched = set(list(itertools.accumulate([N * 10 ** r for r in range(8)])))
    
    for n in range(2, 9):
        
        for r in range(1, n // 2 + 1):
            for a in num_digits[r]:
                for b in num_digits[n - r]:
                    if a == 0 or b == 0:
                        continue
                    for c in (a + b, a - b, b - a, a * b, a // b, b // a):
                        if c == number:
                            return n
                        if c not in searched:
                            num_digits[n].append(c)
                            searched.add(c)
    
    return -1