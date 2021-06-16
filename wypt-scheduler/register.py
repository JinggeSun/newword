# 向注册中心注册
import socket


def get_scheduler_ip():
    global s
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        s.connect(('8.8.8.8', 80))
        ip = s.getsockname()[0]
    finally:
        s.close()
    return ip


def get_scheduler_port():
    global ip
    fin = open('./Dockerfile', 'r', encoding='UTF-8')  # 以读的方式打开文件
    for eachLiine in fin.readlines():  # 读取文件的每一行
        line = eachLiine.strip()  # 去除每行的首位空格
        if 'EXPOSE' in line:
            ip = line.replace('EXPOSE', '').strip()
    fin.close()
    return ip