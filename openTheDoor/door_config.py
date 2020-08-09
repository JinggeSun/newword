from data_util import get_week_and_hour

WEEK_FLAG = 4;
HOUR_MAX_FLAG = 11
HOUR_MIN_FLAG = 8


def open_the_door():
    week,hour = get_week_and_hour()
    if week != WEEK_FLAG:
        return False
    if HOUR_MAX_FLAG > hour < HOUR_MIN_FLAG:
        return False
    return True
