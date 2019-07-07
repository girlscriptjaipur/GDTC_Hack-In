import requests
import sqlite3 as sq
from bs4 import BeautifulSoup as bs


def scrape(db):
    for k in range(1, 4):
        url = "https://www.toptal.com/developers/blog?page=" + str(k)
        r = requests.get(url).content
        content = bs(r, "html.parser")
        con = db.connect()
        if con is not None:
            for i in reversed(content.find_all('div', class_="blog_post_card-content")):
                try:
                    title = i.find('div', class_="blog_post_card__title").text.strip()
                    url = i.find('a', class_="blog_post_card__title-link")['href'].strip()
                    cursor = con.cursor()
                    cursor.execute("Insert into content_agg(source,title, url) values('TopTal',?,?)", (title, url))
                except sq.IntegrityError as e:
                    pass
                except Exception as e:
                    print("Error : ", e)
            con.commit()
            con.close()
            return True
        else:
            return False

