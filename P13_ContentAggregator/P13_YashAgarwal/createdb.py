import sqlite3
from sqlite3 import Error
def create():
	try:
		con=sqlite3.connect('database_content.db')
		
		table_structure="CREATE TABLE IF NOT EXISTS Scrapped_data(source text NOT NULL,link text NOT NULL,title text NOT NULL,Description text NOT NULL,CONSTRAINT PK PRIMARY KEY(source,link,title))"
		if con is not None:
			generate_table(con,table_structure)
			return con
		else:
			print("Error in creating database")
			return None

	except Error as e:
		print("Error in create function in createdb file\n"+e)
		return None

def generate_table(con,table_structure):
	try:
		curr=con.cursor()
		curr.execute(table_structure)
		#curr.execute('insert into Scrapped_data values("Ash","www.org","yash","this is")')
		con.commit()
	except Error as e:
		print(e)	


