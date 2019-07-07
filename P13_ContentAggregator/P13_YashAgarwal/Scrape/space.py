from bs4 import BeautifulSoup as bs
import requests

import createdb as db
url="https://www.space.com/news"
page="space"
u="https://www.space.com"
def scrape_space():
	r=requests.get(url)
	
	sp=bs(r.content,'html5lib')
	div=sp.findAll('div',{'class':'content'})
	heads=sp.findAll('h3',{'class':'article-name'})
	body=sp.findAll('p',{'class':'synopsis'})
	links=sp.findAll('a',{'class':'article-link'})
	j=0;
	#print(div[0].p.text)
	con=db.create()
	if con is not None:
		for i in div:
			curr=con.cursor()		
			headline=heads[j].text
			link=links[j]['href']
			article=body[j].text
			"""print(headline+"\n")
			print(article)
			print(link)"""
			j+=1
			
			curr.execute("INSERT INTO Scrapped_data VALUES(?,?,?,?)",(page,link,headline,article))
			#print(head+"\n" + link + "\n" + a+"\n")
			#f.write(head.replace(",","")+","+link+","+a.replace(",","")+"\n")
	
		con.commit()
		curr.close()
		return True
	else:
		return False
	#f.close()
	

if __name__ == '__main__':
	scrape_space()
