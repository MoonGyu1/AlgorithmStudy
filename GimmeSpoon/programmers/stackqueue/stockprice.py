def solution(prices):
    
    highest = []
    periods = [0 for _ in range(len(prices))]
    
    for i, price in enumerate(prices):
        while len(highest) and price < highest[-1][0]:
            _, j = highest.pop()
            periods[j] = i - j
        highest.append((price, i))
        
    while len(highest):
        _, i = highest.pop()
        periods[i] = len(prices) - 1 - i
            
    return periods