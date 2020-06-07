import React, { Component } from 'react'
import { Redirect } from 'react-router-dom'
import axios from "axios"

export default class SelectShuttle extends Component {
    constructor(props) {
        super(props)
        this.state = {
            missionID: '',
            missionList: [],
            missionSelected: {
                id: "",
                name: "",
                description: "",
                duration: "",
                launchDate: "",
                origin: "",
                cargoID: "",
                coordinatorID: "",
                statusID: "",
                cargoForJourneyQty: "",
                cargoForMissionQty: "",
                cargoForOtherMissionQty: ""
            },
            shuttleList: [],
            shuttleID: '',
            redirect: false
        }
        this.handleMissionChange = this.handleMissionChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleMissionSelect = this.handleMissionSelect.bind(this)
        this.handleShuttleChange = this.handleShuttleChange.bind(this)
        this.handleBack = this.handleBack.bind(this)
    }

    async componentDidMount() {
        // get API for mission
        axios.get("http://localhost:8082/mom/mission/")
            .then(res => {
                console.log(res)
                if (res.data.status === "Success") {
                    this.setState({ missionList: res.data.responseMsg })
                }
            })
            .catch(err => {
                console.log(err)
            })

        axios.get("http://localhost:8083/mom/mission/shuttle")
            .then(res => {
                console.log("shuttle", res)
                if (res.data.status === "Success") {
                    this.setState({
                        shuttleList: res.data.responseMsg
                    })
                }
            })
    }

    handleMissionChange = (e) => {
        // console.log(e.target)
        this.setState({ missionID: e.target.value })
    }

    handleSubmit = (e) => {
        console.log(this.state)
        axios({
            method: "PUT",
            url: "http://localhost:8083/mom/mission/shuttle?",
            params: {
                missionID: this.state.missionID,
                shuttleID: this.state.shuttleID
            }
        })
        .then(res => {
            console.log(res)
            if (res.data.status === "Success") {
                alert("Shuttle successfully added to mission. Returning to home page...")
                this.setState({ redirect: true})
            }
        })
        .catch(err => {
            console.log(err)
        })
    }

    handleMissionSelect = (e) => {
        e.preventDefault()
        if (this.state.missionID === '') {
            alert("No mission selected")
        } else {
            axios({
                method: "GET",
                url: "http://localhost:8082/mom/mission/shuttle?",
                params: {
                    missionID: this.state.missionID
                }
            })
                .then(res => {
                    console.log("response", res)
                    if (res.data.status === "Success") {
                        const json = res.data.responseMsg
                        console.log(json)
                        this.setState({
                            missionSelected: {
                                id: json.mission_id,
                                name: json.mission_name,
                                description: json.mission_details,
                                duration: json.duration,
                                launchDate: json.launch_date,
                                origin: json.country_origin,
                                cargoID: json.cargo_id,
                                coordinatorID: json.coordinator_id,
                                statusID: json.status_id,
                                cargoForJourneyQty: json.cargoForJourneyQuantity,
                                cargoForMissionQty: json.cargoForMissionQuantity,
                                cargoForOtherMissionQty: json.cargoForOtherMissionQuantity
                            }
                        })
                    }
                    if (this.state.missionSelected.cargoForJourneyQty == 0 && this.state.missionSelected.cargoForMissionQty == 0 && this.state.missionSelected.cargoForOtherMissionQty == 0) {
                        alert("Cargo was not selected for mission. Please go back and assign some cargo to this mission")
                    }
                })
        }
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
        // const missionList = this.state.missionList
        // const selectedMission = missionList.filter(o => { return o.mission_id == this.state.missionID })

        // selected shuttle
        const shuttleList = this.state.shuttleList
        const selectedShuttle = shuttleList.filter(shuttle => { return shuttle.shuttle_id == this.state.shuttleID })

        return (
            <div>
                <h4 className="m-3 d-flex justify-content-center">Select Shuttle</h4>
                <label>Select Mission to assign shuttle:</label>
                <select value={this.state.missionID} onChange={this.handleMissionChange} style={{ marginLeft: "10px" }}>
                    <option disabled={true} value="">Select Mission</option>
                    {this.state.missionList.map(o => <option key={o.mission_id} value={o.mission_id}>{o.mission_name}</option>)}
                </select>
                <input type="button" value="Select Mission" onClick={this.handleMissionSelect} style={{ marginLeft: "10px" }} />
                <Line color="black" />
                <h5 className="m-3 d-flex justify-content-center">Mission Details</h5>
                <br />
                <SelectedMission mission={this.state.missionSelected} />
                <Line color="black" />
                <h5 className="m-3 d-flex justify-content-center">Shuttle Details</h5>
                <label>Select Shuttle:</label>
                <select value={this.state.shuttleID} onChange={this.handleShuttleChange} style={{ marginLeft: "10px" }}>
                    <option disabled={true} value="">Select Shuttle</option>
                    {this.state.shuttleList.map(o => <option key={o.shuttle_id} value={o.shuttle_id}>{o.shuttle_name}</option>)}
                </select>
                {selectedShuttle.map(shuttle => <SelectedShuttle key={shuttle.shuttle_id} shuttle={shuttle} />)}
                <br />
                <input type="button" value="Submit" onClick={this.handleSubmit} />
                <input type="button" value="Back" onClick={this.handleBack} style={{ marginLeft: "10px" }} />
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
                value={props.mission.description}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            {/* <label>Mission Location</label>
            <input
                type="text"
                value={props.mission.location}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br /> */}
            <label>Mission Duration (in months):</label>
            <input
                type="text"
                value={props.mission.duration}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            {/* <label>Mission Status:</label>
            <input
                type="text"
                value={props.mission.status_id}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br /> */}
            <label>Cargo For Journey (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForJourneyQty}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo For Mission (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForMissionQty}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo For Other Mission (in Kg):</label>
            <input
                type="text"
                value={props.mission.cargoForOtherMissionQty}
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
                value={props.shuttle.shuttle_name}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Manufacture Year:</label>
            <input
                type="text"
                value={props.shuttle.manfacture_year}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Fuel Capacity (in litres):</label>
            <input
                type="text"
                value={props.shuttle.fuel_capacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Passenger Capacity:</label>
            <input
                type="text"
                value={props.shuttle.passenger_capacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Cargo Capacity (in kgs):</label>
            <input
                type="text"
                value={props.shuttle.cargo_capacity}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
            <label>Travel Speed (in km/hr):</label>
            <input
                type="text"
                value={props.shuttle.travel_speed}
                readOnly={true}
                style={{ marginLeft: "10px" }} />
            <br />
        </div>
    )
}