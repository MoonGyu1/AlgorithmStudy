def solution(arr):

    # results saves the results of all possible operations
    # results[n] is the list of pairs that contains
    # the maximum and minimum value of n consecutive operations
    # at index 0, 1, 2 ..., num_of_operands - n
    results = {1 : [[int(v), int(v)] for v in arr[::2]]}
    operators = arr[1::2]
    
    for n in range(2, len(results[1]) + 1):
        results[n] = [[-1_000_000_000, 1_000_000_000] for _ in range(len(results[n - 1]) - 1)]
        for i, v in enumerate(results[n]):
            for m in range(1, n):
                if operators[i + m - 1] == "+":
                    _v = results[m][i][0] + results[n - m][i + m][0]
                else:
                    _v = results[m][i][0] - results[n - m][i + m][1]
                    
                if _v > results[n][i][0]:
                    results[n][i][0] = _v
                    
                if operators[i + m - 1] == "+":
                    _v = results[m][i][1] + results[n - m][i + m][1]
                else:
                    _v = results[m][i][1] - results[n - m][i + m][0]
                    
                if _v < results[n][i][1]:
                    results[n][i][1] = _v
                    
    return results[len(results[1])][0][0]
