import React, { Component } from 'react';
import AppBar from 'material-ui/AppBar';
import Toolbar from 'material-ui/Toolbar';
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";
import {Card, CardActions, CardHeader, CardText} from 'material-ui/Card';
import {List, ListItem} from 'material-ui/List';
import ActionInfo from 'material-ui/svg-icons/action/info';
import FlatButton from 'material-ui/FlatButton';
import RaisedButton from 'material-ui/RaisedButton';
import SelectField from 'material-ui/SelectField';
import MenuItem from 'material-ui/MenuItem';
import '../css/Help.css';

class Help extends Component {

    constructor(props) {
        super(props);
        this.state = {
          data: [], 
          filter:"all"
          
        };
    }

  handleChange = (e,i,v) => {
    console.log(e.target);
    this.setState({filter:v});
  }

    componentWillMount() {
        let URL = "https://8f555758.ngrok.io/api/getallreqs";
        var xhttp = new XMLHttpRequest();
        let MainComp = this;
        xhttp.onreadystatechange = function() {
            if (this.readyState == 4 && this.status == 200) {
               let c = JSON.parse(xhttp.responseText);
               console.log(c);
                MainComp.setState({
                   data: c.data,
               });
            }
        };
        xhttp.open("GET", URL, true);
        xhttp.send();
    }

    genCard = (details) => (
        <div className='Card'>
        {console.log(details)}
            <Card>
              <CardHeader
                title={details.user.name}
                subtitle={"Mobile Number: " + details.user.phone}
                actAsExpander={true}
                showExpandableButton={true}
              />
              
              <CardActions>
                <div>
                <i style={{fontSize: '12px'}}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Blood Group: {details.user.bloodgroup}</i>
                <p style={{fontSize: '14px'}}>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Has requested for:</p>
                {
                    Object.keys(details.resources).map(item => {
                            if(item != 'desc' && item != 'ppl') {
                                var bool = details.resources[item];
                                if(bool === "true")
                                    return(<FlatButton style={{color:'green'}} label={item.replace("_", " ")} />)
                                else
                                    return(<FlatButton style={{color:'red'}} label={item.replace("_", " ")} />)
                            }
                        }
                    )
                }   
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <RaisedButton style={{marginLeft: '170px'}} secondary={true} label={"Request addressed"} />
                </div>
              </CardActions>
              <CardText expandable={true}>
                <iframe width="20%" height="100%" frameborder="0" scrolling="no" marginheight="0" marginwidth="0" src="https://www.openstreetmap.org/export/embed.html?bbox=-122.11127758026124%2C37.39880087820428%2C-122.07376956939699%2C37.42416186836296&amp;layer=mapnik&amp;marker=37.411509709729124%2C-122.09252255390777" style={{border: '1px solid black'}}>
                </iframe><br/>
                <small>
                    <a href="https://www.openstreetmap.org/?mlat=37.4115&amp;mlon=-122.0925#map=15/37.4115/-122.0925">View Larger Map</a>
                </small>
                {/* <iframe 
                 width="300" 
                 height="170" 
                 frameborder="0" 
                 scrolling="no" 
                 marginheight="0" 
                 marginwidth="0" 
                 src={"https://maps.google.com/maps?q='+" + details.location.latitude + "+','+" + details.location.longitude + "+'&hl=es;z=14&amp;output=embed"}
                >
                </iframe> <br /> <small>
                  <a 
                   href={"https://maps.google.com/maps?q='+" + details.location.latitude + "+','+" + details.location.longitude + "+'&hl=es;z=14&amp;output=embed"}
                   style="color:#0000FF;text-align:left" 
                   target="_blank"
                  >
                    See map bigger
                  </a>
                </small> */}
              </CardText>
            </Card>
            <br />
        </div>
    );


    render() {
        console.log(this.state.data);
        return(
        <MuiThemeProvider>
            <div className="Help">
              <AppBar
                title={<span>Help Requests</span>}
              />
              <SelectField
                floatingLabelText="Filter By"
                floatingLabelStyle={{fontSize:"30px",color:"black"}}
                value={this.state.filter}
                onChange={this.handleChange}
                style={{marginLeft:"50px"}}
              >
                <MenuItem value="all" primaryText="all" />
                <MenuItem value="food" primaryText="food" />
                <MenuItem value="water" primaryText="water" />
                <MenuItem value="shelter" primaryText="shelter" />
                <MenuItem value="firstaid" primaryText="firstaid" />
                <MenuItem value="blankets" primaryText="blankets" />
                <MenuItem value="clothes" primaryText="clothes" />
                <MenuItem value="medical" primaryText="medical" />
                <MenuItem value="transport" primaryText="transport" />
              </SelectField>
              <div className='Items'>
                { this.state.data.map((object) => {
                  console.log(this.state.filter)
                  if( this.state.filter == "all" ){
                    return(this.genCard(object));
                  } else {
                    let filter = this.state.filter;
                    console.log(this.state.filter, object.resources[filter]);
                    if (object.resources[filter] == "true"){
                      return(this.genCard(object));
                    } else {
                      return null
                    }
                  }
                } )}
              </div>
            </div>
        </MuiThemeProvider>
        );
    }
}

export default Help;
