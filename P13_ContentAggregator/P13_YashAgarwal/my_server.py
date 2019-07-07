from flask import Flask, render_template, redirect
import createdb as db
import Scrapper as s
import threading

app = Flask(__name__)

@app.route('/', methods = ["GET"])
def home():
	content = s.getContent()
	if content is None:
		return "Still fetching data.Try again !"
	return render_template('index.html', content = content)

@app.route('/readmore/<source>', methods = ["GET"])
def readmore(source):
	content = s.getContentForSource(source)
	if content is None:
		return redirect('/404')
	return render_template('readmore.html', content = content)


if __name__ == '__main__':
	
	
	thread = threading.Thread(target=s.scrapeStart) 
	thread.daemon = True
	thread.start() 

	
	app.run()
