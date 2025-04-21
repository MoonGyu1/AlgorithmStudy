from collections import deque

def solution(bridge_length, weight, truck_weights):
    bridge = deque()
    bridge_load = 0
    t = 1
    
    for w in truck_weights:
        _t = 0
        while len(bridge) >= bridge_length or bridge_load + w > weight:
            _t, _w = bridge.popleft()
            bridge_load -= _w
        t = max(_t, t)
        bridge.append((t + bridge_length, w))
        bridge_load += w
        t += 1
        
    t, _ = bridge.pop()
    return t