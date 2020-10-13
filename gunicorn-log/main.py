import json
from flask import Flask, request

app = Flask(__name__)

@app.route('/',methods=['GET'])
def hello_world():
    value = 1/0
    return 'Hello, World!'

if __name__ == '__main__':
    app.run('0.0.0.0',debug=True,port=5000)
