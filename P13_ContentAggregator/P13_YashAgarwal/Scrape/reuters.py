from bs4 import BeautifulSoup as bs
import requests

import createdb as db
url="https://in.reuters.com/news/archive/technologyNews"
page="reuters"
u="https://in.reuters.com"
def scrape_reuters():
	r=requests.get(url)
	
	sp=bs(r.content,'html5lib')
	div=sp.findAll('div',{'class':'story-content'})
	heads=sp.findAll('h3',{'class':'story-title'})
	body=sp.findAll('p')
	j=0;
	#print(div[0].p.text)
	con=db.create()
	if con is not None:
		for i in div:
			curr=con.cursor()		
			headline=heads[j].text.strip()
			
			article=body[j].text
			#print(headline+"\n")
			#print(article)
			j+=1
			link=i.a['href']
			link=u+link
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
	scrape_reuters()
