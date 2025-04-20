def solution(s):
    stack = []
    
    for c in s:
        if c == "(":
            stack.append(c)
        elif len(stack) > 0 and stack[-1] == "(":
            stack.pop()
        else:
            return False
        
    if len(stack) == 0:
        return True
    else:
        return False
            