def solution(participant, completion):
    names = set(completion)
    counter = {}
    for name in completion:
        if name not in counter:
            counter[name] = 1
        else:
            counter[name] += 1
    for name in participant:
        if name not in names:
            return name
        else:
            counter[name] -= 1
            if counter[name] == 0:
                names.remove(name)