def solution(answers):
    scores = [0, 0, 0]
    for i, ans in enumerate(answers):
        scores[0] += int((i % 5 + 1) == ans)
        scores[1] += int((((1, 3, 4, 5)[i // 2 % 4]) * (i % 2) - 2 * (i % 2 - 1)) == ans)
        scores[2] += int(((3, 1, 2, 4, 5)[i // 2 % 5]) == ans)
        
    winners = None
    max_score = -1
    for i, score in enumerate(scores):
        if score > max_score:
            winners = [i + 1]
            max_score = score
        elif score == max_score:
            winners.append(i + 1)
            
    return winners