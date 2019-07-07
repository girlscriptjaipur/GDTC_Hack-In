from database import database as db
import time
from ScapeWeb import AndroidPolice, TopTal, college
source = ['AndroidPolice', 'TopTal']
name = ['AndroidPolice', 'TopTal']


def scrapeAll():
    if not AndroidPolice.scrape(db):
        print("ERROR: AndroidPolice SCRAPE ERROR")
    if not TopTal.scrape(db):
        print("ERROR : TopTal SCRAPE ERROR")
    if not college.scapecollege(db):
        print("ERROR : 360Careers SCRAPE ERROR")
    print("SCRAPING COMPLETE")


def getContent():
    content = {}
    conn = db.connect()
    c = conn.cursor()
    for j, i in enumerate(source):
        z = c.execute("Select * from content_agg where source='{}' order by rowid desc;".format(i))
        content[name[j]] = z.fetchall()
        if not content[name[j]]:
            conn.close()
            return None
    conn.close()
    return content


def getContentForSource(s):
    if s in source:
        i = source.index(s)
        content = {}
        conn = db.connect()
        c = conn.cursor()
        z = c.execute("Select * from content_agg where source='{}' order by rowid desc;".format(s))
        content[name[i]] = z.fetchall()
        if not content[name[i]]:
            conn.close()
            return None
        conn.close()
        return content
    else:
        return None


def scrapeStart():
    while True:
        scrapeAll()
        time.sleep(10)


def getContentCollege():
    content={}
    conn = db.connectcollege()
    c = conn.cursor()
    head = "Top Colleges"
    z = c.execute("Select * from college order by rowid desc;")
    content[head] = z.fetchall()
    if not content[head]:
        conn.close()
        return None
    conn.close()
    return content