import functools

def solution(numbers):
    
    for number in numbers:
        if number != 0:
            break
    else:
        return "0"
    
    def cmp(a, b):
        for c1, c2 in zip(a + b, b + a):
            if c1 < c2:
                return 1
            elif c1 > c2:
                return -1
        return 0
    
    numbers = sorted(map(str, numbers), key=functools.cmp_to_key(cmp))
    return "".join(numbers)