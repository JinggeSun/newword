
from flask import Flask, render_template, request

import db_util
from data_util import get_now_time, get_now_time_str
from door_config import open_the_door

app = Flask(__name__)
# 前台使用vue，修改jinja
app.jinja_env.variable_start_string = '[['
app.jinja_env.variable_end_string = ']]'


@app.route('/')
def hello_world():
    return 'Hello World!'




@app.route('/door/save_week_content', methods=['POST'])
def save_week_content():
    data = request.get_json(silent=True)
    week_list = data['weeklyList']
    plan_list = data['planList']
    question_list = data['questionList']

    sql_list = []

    try:
        for week in week_list:
            sql = "INSERT INTO week (content, num, create_time) VALUES ('%s',%s,'%s')"%(week['content'],week['num'],get_now_time_str())
            print(sql)
            sql_list.append(sql)
        db = db_util.database()
        print(sql_list)
        db.update_all(sql_list)
    except Exception as e:
        print(e)
        #db.session.rollback()


    return 'ddd'


@app.route("/door")
def door():
    # 1. 判断时间
    door_flag = open_the_door()
    #if door_flag is True:
    #    return render_template('close_door.html', name='xxx')
    return render_template('door.html', name='ddd')


if __name__ == '__main__':
    app.run(debug=True)
