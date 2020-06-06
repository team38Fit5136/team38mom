import React from "react";
import { Route, Switch, BrowserRouter } from "react-router-dom";
import Login from './components/Login';
import Home from './components/Home'
import Navigation from './components/Navigation'
import CreateMission from './components/CreateMission'
import SelectShuttle from './components/SelectShuttle'

export default function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" component={Login} exact></Route>
                <Route path="/home" component={Home} exact></Route>
                <Route path="/navigation" component={Navigation} exact></Route>
                <Route path="/createmission" component={CreateMission} exact></Route>
                <Route path="/selectshuttle" component={SelectShuttle} exact></Route>
            </Switch>
        </BrowserRouter>
    )
}
