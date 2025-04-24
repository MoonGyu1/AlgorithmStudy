def solution(citations):
    h_index = 0
    for i, c in enumerate(sorted(citations)):
        h_index = max(min(c, len(citations) - i), h_index)
    return h_index