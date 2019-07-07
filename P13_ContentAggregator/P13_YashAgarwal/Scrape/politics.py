from bs4 import BeautifulSoup as bs
import requests

import createdb as db
url="https://www.moneycontrol.com/news/politics/"
page="moneycontrol"
def scrape_money():
	r=requests.get(url)
	
	sp=bs(r.content,'html5lib')
	head=sp.findAll('li',{'class':'clearfix'})
	#article=sp.findAll('div',{'itemprop':'articleBody'})
	j=0;
	
	con=db.create()
	
	if con is not None:
		for i in head:
			curr=con.cursor()		
			headline=i.a['title']
			#print(headline)
			link=i.a['href']
			#print(link)
			#link=page+link
			a=i.p.text
			#print(a)
			
			j+=1
			curr.execute("INSERT INTO Scrapped_data VALUES(?,?,?,?)",(page,link,headline,a))
			#print(head+"\n" + link + "\n" + a+"\n")
			#f.write(head.replace(",","")+","+link+","+a.replace(",","")+"\n")
	
		con.commit()
		curr.close()
		return True
	else:
		return False
	#f.close()
if __name__ == '__main__':
	scrape_money()
