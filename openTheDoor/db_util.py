import pymysql


DB = "database"

class database:
    # 注，python的self等于其它语言的this
    def __init__(self, dbname=None, dbhost=None):
        self._dbname = 'train'
        self._dbhost = '47.104.2.74'
        self._dbuser = 'root'
        self._dbpassword = '/H#aPd%yf99lwypt'
        self._dbcharset = 'utf8'
        self._dbport = int(3306)
        self._conn = self.connectMySQL()
        if (self._conn):
            self._cursor = self._conn.cursor()

# 数据库连接
    def connectMySQL(self):
        conn = False
        try:
            conn = pymysql.connect(host=self._dbhost,
                                   user=self._dbuser,
                                   passwd=self._dbpassword,
                                   db=self._dbname,
                                   port=self._dbport,
                                   cursorclass=pymysql.cursors.DictCursor,
                                   charset=self._dbcharset,
                                   )
        except Exception as data:
            print(data)
            conn = False
        return conn

    # 获取查询结果集
    def fetch_all(self, sql):
        res = ''
        if (self._conn):
            try:
                self._cursor.execute(sql)
                res = self._cursor.fetchall()
            except Exception as data:
                res = False
        return res


    def update(self, sql):
        flag = False
        if (self._conn):
            try:
                self._cursor.execute(sql)
                self._conn.commit()
                flag = True
            except Exception as data:
                flag = False

        return flag

    def update_all(self, sql_list):
        print('----------')
        flag = False
        if (self._conn):
            try:
                for sql in sql_list :
                    self._cursor.execute(sql)
                self._conn.commit()
                flag = True
            except Exception as data:
                print(data)
                flag = False
        return flag

    # 关闭数据库连接
    def close(self):
        if (self._conn):
            try:
                if(type(self._cursor ) =='object'):
                    self._cursor.close()
                if(type(self._conn ) == 'object'):
                    self._conn.close()
            except Exception as data:
                print(data)

