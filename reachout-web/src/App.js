import React, { Component } from 'react';
import './App.css';
import { BrowserRouter as Router, Route } from "react-router-dom";
// import { Provider }  from 'react-redux';

import Help from './components/Help';
import Request from './components/Request';
import Statistics from './components/Statistics';

class App extends Component {
  render() {
    return (
      //  <Provider store={ store }>
         <Router>
           <div>
              <Route exact path='/help' component={Help} />
              <Route exact path='/request' component={Request} />
              <Route exact path='/statistics' component={Statistics} />
              {/* <Path exact path='/' */}
            </div>
         </Router>
      //  </Provider>
    );
  }
}

export default App;
