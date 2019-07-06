import requests
from bs4 import BeautifulSoup as bs
import sqlite3 as sq


def scapecollege(db):
    url = "https://engineering.careers360.com/colleges/ranking"
    r = requests.get(url).content
    content = bs(r, "html.parser")

    con = db.connectcollege()
    if con is not None:

        for i in reversed(content.find_all('td',class_="colgName")):
            try:

                title = i.text.strip()
                url = i.a['href'].strip()
                cursor = con.cursor()
                cursor.execute("Insert into college(title, url) values(?,?)",(title, url))
            except sq.IntegrityError as e:
                pass
            except Exception as e:
                print("Error : ", e)
        con.commit()
        con.close()
        return True
    else:
        return False