from bs4 import BeautifulSoup as bs
import requests
#from urllib.request import urlopen as uop
import createdb as db
def scrape_quora():
	url="https://www.quora.com/topic/Web-Development"
	page="https://www.quora.com"
	source="Quora"
	source1="Quora"
	r=requests.get(url)
	#r=requests.get("https://www.quora.com/topic/Hollywood")
	sp=bs(r.content,'html5lib')
	i=1
	ass=sp.findAll('a',attrs={'class':'question_link'})
	"""filename="quora.txt"
	f=open(filename,"w")"""
	k=0
	con=db.create()
	if con is not None:
		for Qlink in ass:
			Qhref=Qlink['href']
			
			FinalLink=page+Qhref
			r1=requests.get(FinalLink)
			sp1=bs(r1.content,'html5lib')
			span1=sp1.findAll('p',attrs={'class':'ui_qtext_para u-ltr u-text-align--start'})
			
		
			#for p in span1:
			text1=span1[0].text
			#print(Qhref+"\n")
			#print(text1)
			
			curr=con.cursor()
			curr.execute("INSERT INTO Scrapped_data VALUES(?,?,?,?)",(source,FinalLink,Qhref,text1))
				
		con.commit()
		curr.close()
		return True
	
	else:
		return False
	
if __name__ == '__main__':
	t=scrape_quora()	
	
	
