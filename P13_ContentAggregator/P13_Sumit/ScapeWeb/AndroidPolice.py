import requests
import sqlite3 as sq
from bs4 import BeautifulSoup as bs


def scrape(db):
    url = "https://www.androidpolice.com/"
    r = requests.get(url).content
    content = bs(r, "html.parser")
    con = db.connect()
    if con is not None:
        for i in reversed(content.find_all('header', class_="post-header")):
            try:
                x = i.find_all('a')[1]
                cursor = con.cursor()
                cursor.execute("Insert into content_agg(source, title, url) values('AndroidPolice',?,?)", (x.text.strip(), x['href'].strip()))

            except sq.IntegrityError as e:
                pass
            except Exception as e:
                print("Error : ", e)
        con.commit()
        con.close()
        return True
    else:
        return False
