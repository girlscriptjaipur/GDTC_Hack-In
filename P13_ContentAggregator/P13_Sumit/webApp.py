from flask import Flask, render_template, redirect
import ScrapeJob as sj
import threading

app = Flask(__name__)


@app.route('/', methods=["GET"])
def home():
    content = sj.getContent()
    if content is None:
        return "FETCHING DATA PLEASE TRY AGAIN LATER !"
    return render_template('index.html', content=content)


@app.route('/readmore/<source>', methods=["GET"])
def readmore(source):
    content = sj.getContentForSource(source)
    if content is None:
        return redirect('/404')
    return render_template('readmore.html', content=content)

@app.route('/college/', methods=["GET"])
def college():
    content = sj.getContentCollege()
    return render_template('college.html',content=content)



if __name__ == '__main__':
    t1 = threading.Thread(target=sj.scrapeStart)
    t1.daemon = True
    t1.start()
    app.run(debug=True)
