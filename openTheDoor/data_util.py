

# 获取今天是星期几
from datetime import time
import time


def get_now_time():
    cc = time.localtime(time.time())
    return cc

def get_week_and_hour():
    cc = get_now_time();
    week = cc.tm_wday
    hour = cc.tm_hour
    return week,hour
