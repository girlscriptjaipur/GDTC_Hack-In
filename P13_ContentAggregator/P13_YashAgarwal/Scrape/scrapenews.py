from bs4 import BeautifulSoup as bs
import requests
from urllib.request import urlopen as uop
import createdb as db
url="https://inshorts.com/en/read"
page="inshorts"
p1="https://inshorts.com"
def scrape_news():
	r=requests.get(url)
	
	sp=bs(r.content,'html5lib')
	headline=sp.findAll('div',{'class':'news-card-title'})
	article=sp.findAll('div',{'itemprop':'articleBody'})
	j=0;
	con=db.create()
	if con is not None:
		for i in headline:
			curr=con.cursor()		
			head=i.span.text
			link=i.a['href']
			link=p1+link
			a=article[j].text
			j+=1
			curr.execute("INSERT INTO Scrapped_data VALUES(?,?,?,?)",(page,link,head,a))
			#print(head+"\n" + link + "\n" + a+"\n")
			#f.write(head.replace(",","")+","+link+","+a.replace(",","")+"\n")
	
		con.commit()
		curr.close()
		return True
	else:
		return False
	#f.close()
	
