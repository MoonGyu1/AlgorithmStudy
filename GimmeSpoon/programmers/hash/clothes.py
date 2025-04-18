def solution(clothes):
    counter = {}
    for cloth in clothes:
        if cloth[1] in counter:
            counter[cloth[1]] += 1
        else:
            counter[cloth[1]] = 1
        
    answer = 1
    for cloth, n in counter.items():
        answer *= n + 1
    return answer - 1