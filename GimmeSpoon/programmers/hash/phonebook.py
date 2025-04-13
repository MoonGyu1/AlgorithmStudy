def solution(phone_book):

    possible_prefixes = set([])
    
    phone_book = sorted(phone_book, key=lambda x: len(x), reverse=True)
    
    for pn in phone_book:
        
        if pn in possible_prefixes:
            return False
        
        for n in range(1, len(pn) + 1):
            possible_prefixes.add(pn[:n])
            
    return True