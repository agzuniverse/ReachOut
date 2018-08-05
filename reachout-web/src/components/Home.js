import React, { Component } from 'react';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';

class Home extends Component {
    render() {
        return(
           <div className="Home">
                <AppBar position='static'>
                    <Toolbar>
                        <h1>Reach Out</h1>
                    </Toolbar>
                </AppBar>
           </div>
        );
    }
}

export default Home;