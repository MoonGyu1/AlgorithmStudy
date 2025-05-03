def solution(n, costs):
    total_cost = 0
    costs = sorted(costs, key=lambda x: x[2])
    parent = [i for i in range(n)]
    
    def root(i):
        if parent[i] != i:
            return root(parent[i])
        return parent[i]
    
    for i, j, w in costs:
        i, j = root(i), root(j)
        if i != j:
            total_cost += w            
            parent[i] = j 
            
    return total_cost