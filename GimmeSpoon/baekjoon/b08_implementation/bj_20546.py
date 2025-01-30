import sys

inp = list(map(int, sys.stdin.read().split()))
budget, prices = inp[0], inp[1:]

cash_a, cash_b = budget, budget
stock_a, stock_b = 0, 0
flag = 0
last_price = None
for price in prices:
    
    if cash_a >= price:
        stock_a += cash_a // price
        cash_a = cash_a % price
    
    if last_price is not None:
        if last_price < price:
            if flag <= 0:
                flag = 1
            elif flag < 3:
                flag += 1
        if last_price > price:
            if flag >= 0:
                flag = -1
            elif flag > -3:
                flag -= 1

    if flag == 3 and stock_b > 0:
        cash_b += stock_b * price
        stock_b = 0
    if flag == -3 and cash_b >= price:
        stock_b += cash_b // price
        cash_b = cash_b % price

    last_price = price

score = stock_a * last_price + cash_a - stock_b * last_price - cash_b

if score > 0:
    print("BNP")
elif score == 0:
    print("SAMESAME")
else:
    print("TIMING")