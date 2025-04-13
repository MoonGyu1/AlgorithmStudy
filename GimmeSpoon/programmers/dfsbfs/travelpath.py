from collections import defaultdict

def solution(tickets, loc = None, answer = None, target = None):
    
    if isinstance(tickets, list):
        total_tickets = len(tickets)
        graph = defaultdict(list)
        for dept, dest in tickets:
            graph[dept].append(dest)
        for dept, dests in graph.items():
            graph[dept] = sorted(dests)
        return ["ICN"] + solution(graph, "ICN", [], total_tickets)
    else:
        if len(tickets[loc]) == 0:
            return answer
        for i, dest in enumerate(tickets[loc]):
            tickets[loc] = tickets[loc][:i] + tickets[loc][i + 1:]
            ret = solution(tickets, dest, answer + [dest], target)
            tickets[loc] = tickets[loc][:i] + [dest] + tickets[loc][i:]
            if ret is not None and len(ret) == target:
                return ret
