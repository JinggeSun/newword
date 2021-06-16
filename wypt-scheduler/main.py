from apscheduler.executors.pool import ProcessPoolExecutor, ThreadPoolExecutor
from apscheduler.schedulers.background import BlockingScheduler
from apscheduler.jobstores.redis import RedisJobStore


REDIS = {
    'host': '127.0.0.1',
    'port': '6379',
    'db': 0,
    'password': ''
}

jobstores = {
    'redis': RedisJobStore(**REDIS)
}

executors = {
    'default': ThreadPoolExecutor(10),
    'processpool': ProcessPoolExecutor(3)
}

sched = BlockingScheduler(jobstores=jobstores, executors=executors)


def getJobList():
    print(sched.get_jobs(jobstore='redis'))


def add():
    sched.add_job('job_detail:print_hi', 'interval',jobstore='redis', seconds=5, id='job2')  # 每隔5秒执行一次



if __name__ == '__main__':
    try:
        add()
        getJobList()
        sched.print_jobs()
        sched.start()
    except SystemExit:
        print('exit')
        exit()
