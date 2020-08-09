from flask import Flask, render_template, request

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
    print(request)
    data = request.get_json(silent=True)
    week_list = data['weeklyList'],
    plan_list = data['planList']
    question_list = data['questionList']
    print(data['name'])
    return 'ddd'


@app.route("/door")
def door():
    # 1. 判断时间
    door_flag = open_the_door()
    if door_flag is True:
        return render_template('close_door.html', name='xxx')
    return render_template('door.html', name='ddd')


if __name__ == '__main__':
    app.run()
