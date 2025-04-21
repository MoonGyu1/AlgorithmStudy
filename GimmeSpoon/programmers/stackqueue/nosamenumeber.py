def solution(arr):
    answer = []
    for v in arr:
        if len(answer) == 0 or answer[-1] != v:
            answer.append(v)
    return answer