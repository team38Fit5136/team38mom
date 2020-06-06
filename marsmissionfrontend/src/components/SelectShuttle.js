import React, { Component } from 'react'

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
            ]
        }
        this.handleMissionChange = this.handleMissionChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleMissionChange = (e) => {
        console.log(e.target)
        this.setState({ missionID: e.target.value})
    }

    handleSubmit = (e) => {
        const missionList = this.state.missionList
        const mission = missionList.filter(o => {return o.id == this.state.missionID})
        console.log(mission)
        console.log(this.state)
    }

    render() {
        return (
            <div>
                <h4 className="m-3 d-flex justify-content-center">Select Shuttle</h4>
                <label>Select Mission to assign shuttle:</label>
                <select value={this.state.missionID} onChange={this.handleMissionChange}>
                    <option disabled={true} value="">Select Mission</option>
                    {this.state.missionList.map(o => <option key={o.id} value={o.id}>{o.name}</option>)}
                </select>
                <br />
                <h5 className="m-3 d-flex justify-content-center">Mission Details</h5>
                <br />
                <br />
                <input type="button" value="Submit" onClick={this.handleSubmit}/>
            </div>
        )
    }
}
