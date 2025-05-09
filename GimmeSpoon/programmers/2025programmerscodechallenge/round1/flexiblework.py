def solution(schedules, timelogs, startday):
    n_awards = 0
    for target, timelog in zip(schedules, timelogs):
        thrs = target // 100
        tmin = target % 100
        target = (thrs + (tmin + 10) // 60) * 100 + (tmin + 10) % 60
        for day, time in enumerate(timelog):
            if (startday + day - 1) % 7 < 5 and time > target:
                break
        else:
            n_awards += 1

    return n_awards
