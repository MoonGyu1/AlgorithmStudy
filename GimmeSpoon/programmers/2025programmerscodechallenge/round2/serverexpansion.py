import collections

def solution(players, m, k):
    num_expanded = 0
    limit = m - 1
    extra_servers = collections.deque()

    for t, player in enumerate(players):
        while len(extra_servers) > 0 and extra_servers[0] <= t:
            extra_servers.popleft()
            limit -= m
        while limit < player:
            num_expanded += 1
            limit += m
            extra_servers.append(t + k)

    return num_expanded
