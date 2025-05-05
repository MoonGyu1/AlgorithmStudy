def solution(numbers, target, v = 0):

    if len(numbers) == 0:
        if target == v:
            return 1
        else:
            return 0
    _v = numbers[-1]
    
    return solution(numbers[:-1], target, v + _v) + solution(numbers[:-1], target, v - _v)
