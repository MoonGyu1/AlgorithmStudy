def solution(money):
    max_stolen = [(money[0], 0), (money[0], money[1])]
    for i, m in enumerate(money[2:]):
        max_stolen.append((
            max(max_stolen[-2][0] + m, max_stolen[-1][0]),
            max(max_stolen[-2][1] + m, max_stolen[-1][1]),
        ))
    return max(max_stolen[-1][1], max_stolen[-2][0], max_stolen[-2][1], max_stolen[-3][0])