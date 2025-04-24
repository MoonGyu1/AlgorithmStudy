all_possible_cases = (0, 1, 6, 31, 156, 781)
ch_idx = {"A":0, "E":1, "I":2, "O":3, "U":4}

def solution(word):
    answer = 0
    for i, c in enumerate(word):
        answer += all_possible_cases[5 - i] * ch_idx[c]
        answer += 1
    return answer