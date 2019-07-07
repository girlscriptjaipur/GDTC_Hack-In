import createdb as db
from Scrape import scrape,scrapenews,reuters,politics,sports,space
import time

source = ['inshorts','moneycontrol', 'reuters','indiatoday','space'] 
name = ['Inshorts','MoneyControl','Reuters India','IndiaToday','Space']

def start_scrapper():
	if not scrapenews.scrape_news():
		print("Error in fetching newsin short")
	if not politics.scrape_money():
		print("Error in fetching MOney Control")
	if not reuters.scrape_reuters():
		print("Error in fetching reuters")
	if not sports.scrape_sports():
		print("Error in fetching indiatoday")
	if not space.scrape_space():
		print("Error in fetching space.com")
	
	print("Done Scrapping Check db")

def getContent():
	content = {}
	conn = db.create()
	c = conn.cursor()
	
	for j,i in enumerate(source) :
		z = c.execute("Select * from Scrapped_data where source='{}' order by rowid desc;".format(i));  
		content[name[j]] = z.fetchall()
		if content[name[j]] ==[]:
			conn.close()
			return None
	conn.close()
	return content

def getContentForSource(s):
	if s in source :
		i = source.index(s)
		content = {}
		conn = db.create()
		c = conn.cursor()
		z = c.execute("Select * from Scrapped_data where source='{}' order by rowid desc;".format(s));  
		content[name[i]] = z.fetchall()
		if content[name[j]] ==[]:
			conn.close()
			return None
		conn.close()
		return content
	else:
		return None

def scrapeStart():
	while True:
		start_scrapper()
		time.sleep(3600) 
	
if __name__ == '__main__':
	start_scrapper()
