def solution(n, computers):
    searched = [False for _ in range(n)]
    num_subnetworks = 0
    for i, contains in enumerate(searched):
        if searched[i] is False:
            searches = []
            for j, connected in enumerate(computers[i]):
                if connected == 1:
                    searches.append(j)
            while len(searches) > 0:
                j = searches.pop()
                searched[j] = True
                for _j, connected in enumerate(computers[j]):
                    if connected == 1 and searched[_j] is False:
                        searches.append(_j)
            searched[i] = True
            num_subnetworks += 1
            
    return num_subnetworks