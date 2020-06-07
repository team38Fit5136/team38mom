import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'

export default class SelectShuttle extends Component {
    constructor(props) {
        super(props)
        this.state = {
            missionID: '',
            missionList: [
                {
                    name: "Elysium Mons Infra Project",
                    id: 7003,
                    missionDesc: "To build basic infrastructure",
                    location: "120N, 55E",
                    duration: "32",
                    status: "Departed Earth",
                    cargoForJourney: "100",
                    cargoForMision: "250",
                    cargoForOtherMission: "300"
                },
                {
                    name: "Olympus Mons Climbing Expedition",
                    id: 7004,
                    missionDesc: "To climb and discover the summit",
                    location: "133.1N, 140E",
                    duration: "33",
                    status: "Landed On Mars",
                    cargoForJourney: "121",
                    cargoForMision: "600",
                    cargoForOtherMission: "100"
                },
                {
                    name: "Valles Marineris Railway Project",
                    id: 7005,
                    missionDesc: "To build railway infrastructure",
                    location: "124N, 55E",
                    duration: "32",
                    status: "Departed Earth",
                    cargoForJourney: "111",
                    cargoForMision: "256",
                    cargoForOtherMission: "370"
                },
                {
                    name: "Medusae Fossae City Building Prohect",
                    id: 7006,
                    missionDesc: "To build basic infrastructure",
                    location: "120N, 55E",
                    duration: "32",
                    status: "Departed Earth",
                    cargoForJourney: "1045",
                    cargoForMision: "254",
                    cargoForOtherMission: "376"
                }
            ],
            shuttleList: [
                {
                    id: 765,
                    name: "magna. Sed",
                    manufactureYear: "10/04/2018",
                    fuelCapacity: 977899900,
                    passengerCapacity: 249,
                    cargoCapacity: 502,
                    travelSpeed: 31837,
                    origin: "Russion Federation"
                },
                {
                    id: 770,
                    name: "dapibus gravida.",
                    manufactureYear: "04/17/2014",
                    fuelCapacity: 922124065,
                    passengerCapacity: 249,
                    cargoCapacity: 1203,
                    travelSpeed: 34676,
                    origin: "United Kingdom"
                }
            ],
            shuttleID: '',
            redirect: false
        }
        this.handleMissionChange = this.handleMissionChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleMissionSelect = this.handleMissionSelect.bind(this)
        this.handleShuttleChange = this.handleShuttleChange.bind(this)
        this.handleBack = this.handleBack.bind(this)
    }

    handleMissionChange = (e) => {
        // console.log(e.target)
        this.setState({ missionID: e.target.value })
    }

    handleSubmit = (e) => {
        // const missionList = this.state.missionList
        // const mission = missionList.filter(o => { return o.id == this.state.missionID })
        // console.log(mission)
        console.log(this.state)
    }

    handleMissionSelect = (e) => {
        e.preventDefault()
    }

    handleShuttleChange = (e) => {
        this.setState({ shuttleID: e.target.value })
    }

    handleBack = () => {
        this.setState({ redirect: true })
    }

    render() {
        // redirect page for Back button
        if (this.state.redirect) {
            return <Redirect push to="/navigation" />
        }

        // selected mission
        const missionList = this.state.missionList
        const selectedMission = missionList.filter(o => { return o.id == this.state.missionID })

        // selected shuttle
        const shuttleList = this.state.shuttleList
        const selectedShuttle = shuttleList.filter(shuttle => { return shuttle.id == this.state.shuttleID })

        return (
            <div>
                <h4 className="m-3 d-flex justify-content-center">Select Shuttle</h4>
                <label>Select Mission to assign shuttle:</label>
                <select value={this.state.missionID} onChange={this.handleMissionChange} style={{ marginLeft: "10px" }}>
                    <option disabled={true} value="">Select Mission</option>
                    {this.state.missionList.map(o => <option key={o.id} value={o.id}>{o.name}</option>)}
                </select>
                <Line color="black" />
                <h5 className="m-3 d-flex justify-content-center">Mission Details</h5>
                <br />
                {selectedMission.map(mission => <SelectedMission key={mission.id} mission={mission} />)}
                <Line color="black" />
                <h5 className="m-3 d-flex justify-content-center">Shuttle Details</h5>
                <label>Select Shuttle:</label>
                <select value={this.state.shuttleID} onChange={this.handleShuttleChange} style={{ marginLeft: "10px" }}>
                    <option disabled={true} value="">Select Shuttle</option>
                    {this.state.shuttleList.map(o => <option key={o.id} value={o.id}>{o.name}</option>)}
                </select>
                {selectedShuttle.map(shuttle => <SelectedShuttle key={shuttle.id} shuttle={shuttle} />)}
                <br />
                <input type="button" value="Submit" onClick={this.handleSubmit} />
                <input type="button" value="Back" onClick={this.handleBack} style={{ marginLeft: "10px" }}/>
            </div>
        )
    }
}

const Line = ({ color }) => {
    return (
        <hr style={{
            color: color,
            backgroundColor: color,
            height: 3
        }}
        />
    )
}

// render component based on selected mission from dropdown
const SelectedMission = (props) => {
    return (
        <div>
            <label>Mission Name:</label>
            <input
                type="text"
                value={props.mission.name}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Mission Description:</label>
            <input
                type="text"
                value={props.mission.missionDesc}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Mission Location</label>
            <input
                type="text"
                value={props.mission.location}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Mission Duration (in months):</label>
            <input
                type="text"
                value={props.mission.duration}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Mission Status:</label>
            <input
                type="text"
                value={props.mission.status}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo For Journey (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForJourney}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo For Mission (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForMision}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo For Other Mission (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForOtherMission}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
        </div>
    )
}

const SelectedShuttle = (props) => {
    return (
        <div>
            <label>Shuttle Name:</label>
            <input
                type="text"
                value={props.shuttle.name}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Manufacture Year:</label>
            <input
                type="text"
                value={props.shuttle.manufactureYear}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Fuel Capacity (in litres):</label>
            <input
                type="text"
                value={props.shuttle.fuelCapacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Passenger Capacity:</label>
            <input
                type="text"
                value={props.shuttle.passengerCapacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo Capacity (in kgs):</label>
            <input
                type="text"
                value={props.shuttle.cargoCapacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Travel Speed (in km/hr):</label>
            <input
                type="text"
                value={props.shuttle.travelSpeed}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Origin:</label>
            <input
                type="text"
                value={props.shuttle.origin}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
        </div>
    )
}