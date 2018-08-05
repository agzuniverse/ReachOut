import React, { Component } from "react";
import AppBar from "material-ui/AppBar";
import Toolbar from "material-ui/Toolbar";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import { Card, CardActions, CardHeader, CardText } from "material-ui/Card";
import FlatButton from "material-ui/FlatButton";
import Paper from 'material-ui/Paper';
import { Bar } from 'react-charts';
import { ColumnChart, BarChart } from 'react-chartkick';

import Chart from 'chart.js'

class Statistics extends Component {
  constructor(props) {
    super(props);
    this.state = {
      request: [],
      help: [],
      processData: {},
    };
  }

  componentWillMount() {
    let request = [],
      help = [];
    let mainComp = this;
    let URL1 = "https://8f555758.ngrok.io/api/getalloffers";
    let URL2 = "https://8f555758.ngrok.io/api/getallreqs";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
      if (this.readyState == 4 && this.status == 200) {
        console.log(xhttp.responseText);
         request = JSON.parse(xhttp.responseText);
        console.log(request);
        request = request.data;
        request.shift();
        mainComp.setState({
          request: request
        });
        var xhttp2 = new XMLHttpRequest();
        xhttp2.onreadystatechange = function() {
          if (this.readyState == 4 && this.status == 200) {
            // console.log(xhttp.responseText)
            help = JSON.parse(xhttp2.responseText);
            mainComp.setState({
              help: help
            });
            mainComp.processData();
            console.log(help);
          }
        };
        xhttp2.open("GET", URL2, true);
        xhttp2.send();
      }
    };
    xhttp.open("GET", URL1, true);
    xhttp.send();
  }

  processData = () => {
    let house_ask = 0;  
    let house_provide = 0;
    let food_ask = 0;
    let food_provide = 0;
    let blood_ask = 0;
    let blood_provide = 0;
    if(this.state.help.data) {
      this.state.help.data.map(object => {
        if(object.resources.shelter === "true")
          house_ask = house_ask + 1;
        if(object.resources.food == "true")
          food_ask = food_ask + 1;
        if(object.resources.blood == "true")
          blood_ask = blood_ask + 1;
      }) 
    }
    if(this.state.request) {
      this.state.request.map(object => {
        console.log(object);
        if(object.resources.shelter == "true")
          house_provide = house_provide + 1;
        if(object.resources.food == "true")
          food_provide = food_provide + 1;
        if(object.resources.blood == "true")
          blood_provide = blood_provide + 1;
      }) 
    }
    this.setState({
      processData: {
        house_ask: house_ask,
        house_provide: house_provide,
        food_ask: food_ask,
        food_provide: food_provide,
        blood_ask: blood_ask,
        blood_provide: blood_provide,
        needHelp: this.state.help.data.length,
        receiveHelp: this.state.request.length,
      }
    });
  }

  render() {
    console.log(this.state);
    const style = {
      // height: 100,
      // width: 100,
      margin: 30,
      textAlign: 'center',
      display: 'inline-block',
    };  
    return (
      <MuiThemeProvider>
        <div className="Statistics">
          <AppBar title={<span>Analytics</span>}/>
            <ColumnChart style={{marginLeft: '30px'}} width="800px" data={[["Need Help", this.state.processData.needHelp], ["Willing to help", this.state.processData.receiveHelp]]} />
          <div className='row'> 
            <span>
            <Paper style={style} zDepth={1}>
              <h3>&nbsp; No. of people asking for shelter: {this.state.processData.house_ask}&nbsp;&nbsp;</h3>   
            </Paper>
            </span>
            <span style={{paddingLeft: '150px'}}>
              <Paper style={style} zDepth={1}>
                <h3>&nbsp; No. of people willing to provide shelter: {this.state.processData.house_provide}&nbsp;&nbsp;</h3>   
              </Paper>
            </span>
          </div>
          <BarChart width="800px" data={[["Need shelter", this.state.processData.house_ask], ["willing to provide Shelter", this.state.processData.house_provide]]} />
          <div className='row2'>
            <span>
              <Paper style={style} zDepth={1}>
                <h3>&nbsp; No. of people asking for blood: {this.state.processData.blood_ask}&nbsp;&nbsp;</h3>   
              </Paper>
            </span>
            <span style={{paddingLeft: '150px'}}>
              <Paper style={style} zDepth={1}>
                <h3>&nbsp; No. of people willing to provide blood: {this.state.processData.blood_provide}&nbsp;&nbsp;</h3>   
              </Paper>
            </span>
          </div>
          <BarChart width="800px" data={[["Need Blood", this.state.processData.blood_ask], ["willing to Provide Blood", this.state.processData.blood_provide]]} />
          <div className='row3'>
             <span>
              <Paper style={style} zDepth={1}>
                <h3>&nbsp; No. of people asking for food: {this.state.processData.food_ask}&nbsp;&nbsp;</h3>   
              </Paper>
            </span>
            <span style={{paddingLeft: '150px'}}>
              <Paper style={style} zDepth={1}>
                <h3>&nbsp; No. of people willing to provide food: {this.state.processData.food_provide}&nbsp;&nbsp;</h3>   
              </Paper>
            </span>        
          </div>
          <BarChart width="800px" data={[["Need Food", this.state.processData.food_ask], ["willing to provide Food", this.state.processData.food_provide]]} />
        </div>
      </MuiThemeProvider>
    );
  }
}

export default Statistics;
