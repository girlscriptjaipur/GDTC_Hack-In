import sqlite3
from sqlite3 import Error


def connect():
    try:
        conn = sqlite3.connect('content.db')
        sql_create_projects_table = "CREATE TABLE IF NOT EXISTS content_agg (source text NOT NULL,title text NOT NULL,url text NOT NULL, CONSTRAINT PK PRIMARY KEY(source,title,url));"
        if conn is not None:
            create_table(conn, sql_create_projects_table)
            return conn
        else:
            print("Error! cannot create the database connection.")
            return None
    except Error as e:
        print(e)
        return None


def create_table(conn, create_table_sql):
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
        conn.commit()
    except Error as e:
        print("Error :", e)


def connectcollege():
    try:
        conn = sqlite3.connect('content.db')
        sql_create_projects_table = "CREATE TABLE IF NOT EXISTS college (title text NOT NULL,url text NOT NULL, CONSTRAINT PK PRIMARY KEY(title,url));"
        if conn is not None:
            create_table(conn, sql_create_projects_table)
            return conn
        else:
            print("Error! cannot create the database connection.")
            return None
    except Error as e:
        print(e)
        return None


def create_tablecollege(conn, create_table_sql):
    try:
        c = conn.cursor()
        c.execute(create_table_sql)
        conn.commit()
    except Error as e:
        print("Error :", e)
