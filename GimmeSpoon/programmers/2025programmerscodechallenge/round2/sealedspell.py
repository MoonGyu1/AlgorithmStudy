def solution(n, bans):
    dictionary = {chr(x): i + 1 for i, x in enumerate(range(ord("a"), ord("z") + 1))}
    bans = sorted(list(map(lambda x: sum([dictionary[c] * (26 ** i) for i, c in enumerate(reversed(x))]), bans)))

    # Find the order of the spell with bans
    for ban in bans:
        if ban <= n:
            n += 1
        else:
            break

    # Construct a string based on the order
    spell = []
    while n > 0:
        spell.append(chr((n - 1) % 26 + ord('a')))
        n = (n - 1) // 26

    return "".join(reversed(spell))
