from bs4 import BeautifulSoup as bs
import requests
#from urllib.request import urlopen as uop
import createdb as db
myurl="https://www.newegg.com/global/in-en/Gaming-Laptops/SubCategory/ID-3365?Tid=1297731"
source="newegg"
def scrape_newegg():

	r=requests.get(myurl)
	s=bs(r.content,'html5lib')
	container=s.findAll('div',{'class':'item-container'})
	
	con=db.create()
	
	for contain in container:
		curr=con.cursor()
		price=contain.find("li","price-current").text.strip()
		brand=contain.find("div","item-branding").img["title"].strip()+"\n"+price
		product=contain.find("a","item-title").text.strip()
		url=contain.find('a',"item-title")
		linkk=url['href']
		#price.strip()
		
		
		curr.execute("INSERT INTO Scrapped_data VALUES(?,?,?,?)",(source,linkk,brand,product))
		#f.write(brand + "," + product.replace(",","|") + "," + price.replace(",",""))
	
	con.commit()
	curr.close()
	#f.close()
	
