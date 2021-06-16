from apscheduler.executors.pool import ThreadPoolExecutor, ProcessPoolExecutor
from apscheduler.jobstores.redis import RedisJobStore
from flask import Flask, render_template
from flask_apscheduler import APScheduler

# reids 配置
REDIS = {
    'host': '127.0.0.1',
    'port': '6379',
    'db': 0,
    'password': ''
}

# jobstores
jobstores = {
    'redis': RedisJobStore(**REDIS)
}

# 可以通过max_instances在添加任务时使用关键字参数来设置调度程序允许并发运行的特定任务的最大实例数。
job_defaults = {
    'coalesce': False,  # 默认情况下关闭新的作业
    'max_instances': 3  # 设置调度程序将同时运行的特定作业的最大实例数3
}

# 执行配置
executors = {
    'default': ThreadPoolExecutor(10),
    'processpool': ProcessPoolExecutor(3)
}


# 配置类
class Config(object):
    SCHEDULER_API_ENABLED = True
    SCHEDULER_JOBSTORES = jobstores
    SCHEDULER_EXECUTORS = executors


# flak
app = Flask(__name__)
app.config.from_object(Config())

# initialize scheduler
scheduler = APScheduler()

# 修改flask默认模版引擎转义符
# app.jinja_env.variable_start_string = '[['
# app.jinja_env.variable_end_string = ']]'


# if you don't wanna use a config, you can set options here:
# scheduler.api_enabled = True
scheduler.init_app(app)


scheduler.add_job(func='alg.job_detail:print_hi', trigger = 'cron', jobstore='redis', second='5/10', id='job9983')  # 每隔5秒执行一次


#scheduler.add_job(func='job_detail:print_hi', trigger = 'cron', second = '*/20',jobstore='redis', id='jo222b3',name="dxxxxxx")
scheduler.start()


@app.route('/')  # 定义“路由”，路由是什么不知道没关系，下面会讲
def hello():
    return render_template('index.html')



if __name__ == '__main__':
    app.run()
