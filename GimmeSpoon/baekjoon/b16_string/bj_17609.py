import sys

def palindrome(sentence:str):
    i, j = 0, -1
    while i - j < len(sentence):
        if sentence[i] != sentence[j]:
            return (i, j)
        i += 1
        j -= 1
    return None

for line in sys.stdin.readlines()[1:]:
    line = line.strip()

    res = palindrome(line)
    if isinstance(res, tuple):
        i, j = res

    print(-1)
