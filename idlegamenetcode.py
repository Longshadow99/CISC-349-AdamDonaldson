from flask import Flask, request, jsonify 
from flask_mysqldb import MySQL 

app = Flask(__name__)

app.config['MYSQL_USER'] = 'sql5407588'
app.config['MYSQL_PASSWORD'] = 'V27DWa9qcr'
app.config['MYSQL_HOST'] = 'sql5.freemysqlhosting.net'
app.config['MYSQL_DB'] = 'sql5407588'
app.config['MYSQL_CURSORCLASS'] = 'DictCursor'

mysql = MySQL(app)

@app.route("/login", methods=["GET", "POST"])
def logIn():
    content = request.get_json()
    user = content['username']
    pss = content['password']
    cur = mysql.connection.cursor()

    cur.execute("SELECT * FROM accounts WHERE username ='{}' AND password ='{}'".format(user, pss))

    
    results = cur.fetchall()

    if len(results) == 0:
        print("here")
        data = {'AID': 0}
        return jsonify(data)
    else:
        data = results[0]
        return jsonify(data)

@app.route("/save", methods=["GET", "POST"])
def save():
    content = request.get_json()
    user = content['username']
    num = content['num']
    rate1 = content['rate1']
    rate2 = content['rate2']
    rate3 = content['rate3']
    rate4 = content['rate4']

    cur = mysql.connection.cursor()
    cur.execute("UPDATE accounts SET num ='{}',rate1 ='{}',rate2 ='{}',rate3 ='{}',rate4 ='{}' WHERE username ='{}'".format(num, rate1, rate2, rate3, rate4, user))
    mysql.connection.commit()
    return "Success!"

@app.route("/signup", methods=["GET", "POST"])
def signUp():
    content = request.get_json()
    user = content['username']
    pss = content['password']
    cur = mysql.connection.cursor()

    cur.execute("SELECT * FROM accounts WHERE username ='{}'".format(user))
    results = cur.fetchall()

    print(len(results))
    print(user, pss)
    if len(results) == 0:
        cur.execute("INSERT INTO `accounts` VALUES (NULL, '{}', '{}', '0', '1', '0', '0', '0')".format(user,pss))
        mysql.connection.commit()

        data = {'Success': 0}
        return jsonify(data)


if __name__ == '__main__':
    app.run(host='192.168.254.67')

    