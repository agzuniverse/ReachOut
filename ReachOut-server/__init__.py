from flask import Flask
from flask import jsonify
from flask import request
from flask_pymongo import PyMongo
from flask_cors import CORS
import json
import sys

app = Flask(__name__)
CORS(app)

app.config['MONGO_DBNAME'] = 'test'
app.config['MONGO_URI'] = 'mongodb://localhost:27017/test'

mongo = PyMongo(app)

@app.route('/api/getallreqs', methods=['GET'])
def get_all_reqs():
  helpreq = mongo.db.helpreqs
  output = []
  for s in helpreq.find():
    s["_id"] = "Placeholder"
    output.append(s);
  res = str({"data":output})
  res = res.replace('\'','"')
  print(res)
  return res

@app.route('/api/getalloffers', methods=['GET'])
def get_all_offers():
  helpoffer = mongo.db.helpoffers
  output = []
  for s in helpoffer.find():
    s["_id"] = "Placeholder" 
    output.append(s)
  res = str({"data":output})
  res = res.replace('\'','"')
  print(res)
  return res

@app.route('/api/posthelpoffer', methods=['POST'])
def add_offer():
  helpoffer = mongo.db.helpoffers
  offer_id = helpoffer.insert(request.json)
  return str(offer_id)

@app.route('/api/posthelp', methods=['POST'])
def add_req():
  helpreq = mongo.db.helpreqs
  req_id = helpreq.insert(request.json)
  return str(req_id)

if __name__ == '__main__':
    app.run(debug=True)
    print("server running....")