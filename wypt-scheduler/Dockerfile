FROM python:3.5.7
WORKDIR /app
ADD . /app
RUN pip install -r requirements.txt --index-url=http://192.168.5.59:8000/simple/
EXPOSE 8000
ENV NAME WORLD
CMD ['python','flask-sch.py']