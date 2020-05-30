import React, { Component } from 'react'
import { Multiselect } from 'multiselect-react-dropdown'


export default class CreateMission extends Component {
    constructor(props) {
        super(props)
        this.state = {
            missionName: '',
            missionDescription: '',
            countryOrigin: '',
            countryAllowed: [],
            launchDate: '',
            duration: '',
            coordinatorName: '',
            coordinatorContact: '',
            missionStatus: '',
            location: '',
            cargoForJourney: '',
            cargoForMission: '',
            cargoForOtherMission: '',
            employeeTitle: '',
            noOfEmployee: '',
            jobName: '',
            jobDescription: ''
        }
        this.change = this.change.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }


    change = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();     // prevents default behavior of putting stae values in URL
        console.log(this.state)
    }

    handleChange = (e) => {
        this.setState({
            countryOrigin: e.target.value

        })
    }

    countryOptions = [
        {
            id: 'AU',
            name: 'Australia'
        },
        {
            id: 'IN',
            name: 'India'
        },
        {
            id: 'UK',
            name: 'United Kingdom'
        },
        {
            id: 'US',
            name: 'United States'
        },
    ]

    missionStatusFields = [
        {
            id: 'DEPRT',
            name: 'Departed Earth'
        },
        {
            id: 'LNDM',
            name: 'Landed on Mars'
        },
        {
            id: 'INPROG',
            name: 'Mission in Progess'
        },
        {
            id: 'RTRNE',
            name: 'Returned to Earth'
        },
        {
            id: 'CMPLT',
            name: 'Mission Completed'
        },
    ]

    render() {
        return (
            <div>
                <h2>Create Mission</h2>
                <form>
                    <label>First Name: </label>
                    <input
                        name="missionName"
                        placeholder="Enter Mission Name"
                        value={this.state.missionName}
                        onChange={e => this.change(e)} />
                    <br />
                    <label>Mission Description: </label>
                    <input
                        name="missionDescription"
                        placeholder="Enter Mission Description"
                        value={this.state.missionDescription}
                        onChange={e => this.change(e)} />
                    <br />
                    <label>Country of Origin: </label>
                    <select value={this.state.countryOrigin} onChange={e => this.handleChange(e)}>
                        <option disabled={true} value="">Select Country</option>
                        {this.countryOptions.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <br />
                    <label>Allowed Countries: </label>
                    <Multiselect
                    // options={this.}
                    />
                    <br />
                    <label>Coordinator Name: </label>
                    <input
                        name="coordinatorName"
                        placeholder="Enter Mission Description"
                        value={this.state.coordinatorName}
                        onChange={e => this.change(e)} />
                    <br />
                    <label>Coordinator Contact Info: </label>
                    <input
                        name="coordinatorContact"
                        placeholder="Enter Mission Description"
                        value={this.state.coordinatorContact}
                        onChange={e => this.change(e)} />
                    <br />
                    <label>Location: </label>
                    <input
                        name="location"
                        placeholder="Enter Mission Description"
                        value={this.state.location}
                        onChange={e => this.change(e)} />
                    <br />
                    <label>Mission Status </label>
                    <select value={this.state.missionStatus} onChange={e => this.handleChange(e)}>
                        <option disabled={true} value="">Select Mission</option>
                        {this.missionStatusFields.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <br />
                    <button onClick={this.handleSubmit}>Submit</button>
                </form>
            </div>
        )
    }
}
