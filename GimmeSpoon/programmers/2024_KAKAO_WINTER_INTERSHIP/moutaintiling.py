from itertools import combinations
from collections import Counter

def solution(dice):
    
    def possible_sums(indexes):
        sums = None
        for index in indexes:
            if sums is not None:
                temp = []
                for num in dice[index-1]:
                    temp += [s + num for s in sums]
                sums = temp
            else:
                sums = dice[index-1]
                
        return sums
    
    secret_dice = None
    victories = 0
    for a_dice in combinations((list(range(1, len(dice) + 1))), int(len(dice) / 2)):
        
        b_dice = [i for i in range(1, len(dice) + 1) if i not in a_dice]
        a_sums, b_sums = possible_sums(a_dice), possible_sums(b_dice)

        a_sums, b_sums = Counter(a_sums), Counter(b_sums)
        
        wins = 0
        draws = 0
        losses = 0
        for a, v in a_sums.items():
            for b in b_sums.keys():
                if a > b:
                    wins += v * b_sums[b]
                elif a == b:
                    draws += v * b_sums[b]
                else:
                    losses += v * b_sums[b]
        
        if wins > victories:
            victories = wins
            secret_dice = a_dice
            
    return secret_dice