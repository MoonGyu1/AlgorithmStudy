import heapq

def solution(coin, cards):
    
    M = len(cards) // 2
    modnlst = [0 for _ in range(M)]
    lives = []
    heapq.heapify(lives)
    initial_cards = set()
    
    for card in cards[:len(cards) // 3]:
        i = M - abs(card - M - 1) - card // (M + 1)
        initial_cards.add(i)
        modnlst[i] += 1
        if modnlst[i] == 2:
            heapq.heappush(lives, 0)
        
    r = 1
    for i, card in enumerate(cards[len(cards) // 3:]):
        
        card = M - abs(card - M - 1) - card // (M + 1)
        modnlst[card] += 1
        if modnlst[card] == 2:
            if card in initial_cards:
                heapq.heappush(lives, 1)
            else:
                heapq.heappush(lives, 2)
            
        
        if i % 2 == 1:
            if len(lives) > 0 and lives[0] <= coin:
                price = heapq.heappop(lives)
                coin -= price
                r += 1
            else:
                break
            
    return r