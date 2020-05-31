import React, { Component } from 'react'
import { Multiselect } from 'multiselect-react-dropdown'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";


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
            employmentRequirements: [],
            jobName: '',
            jobDescription: '',
            jobRequirements: []
        }
        this.handleInputChange = this.handleInputChange.bind(this)
        this.handleCountryOriginSelect = this.handleCountryOriginSelect.bind(this)
        this.handleMissionStatusSelect = this.handleMissionStatusSelect.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleMultiSelect = this.handleMultiSelect.bind(this)
        this.onNumberInput = this.onNumberInput.bind(this)
        this.handleOnFileChange = this.handleOnFileChange.bind(this)
        this.handleEmployeeTitleSelect = this.handleEmployeeTitleSelect.bind(this)
        this.handleAddEmployment = this.handleAddEmployment.bind(this)
        this.handleAddJob = this.handleAddJob.bind(this)
        this.handleDateChange = this.handleDateChange.bind(this)
    }


    handleInputChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();     // prevents default behavior of putting state values in URL
        console.log(this.state)
    }

    handleCountryOriginSelect = (e) => {
        this.setState({
            countryOrigin: e.target.value

        })
    }

    handleMissionStatusSelect = (e) => {
        this.setState({
            missionStatus: e.target.value

        })
    }

    handleMultiSelect = (selectedList) => {
        this.setState({
            countryAllowed: selectedList
        })
    }

    onNumberInput = (e) => {
        this.setState({
            [e.target.name]: e.currentTarget.value
        })
    }

    handleOnFileChange = (e) => {
        this.setState({
            [e.target.name]: e.target.files[0]
        })
    }

    handleEmployeeTitleSelect = (e) => {
        this.setState({
            employeeTitle: e.target.value
        })
    }

    handleAddEmployment = (e) => {
        e.preventDefault()
        let object = {
            'title': this.state.employeeTitle,
            'count': this.state.noOfEmployee
        }
        this.setState(prevState => {
            let employmentRequirements = prevState.employmentRequirements.concat(object)
            return {
                employmentRequirements
            }
        })
        alert(this.state.noOfEmployee + " " + this.state.employeeTitle + " added")
    }

    handleJobNameSelect = (e) => {
        this.setState({
            jobName: e.target.value
        })
    }

    handleAddJob = (e) => {
        e.preventDefault()
        let object = {
            'jobName': this.state.jobName,
            'jobDescription': this.state.jobDescription
        }
        this.setState(prevState => {
            let jobRequirements = prevState.jobRequirements.concat(object)
            return {
                jobRequirements
            }
        })
        alert(this.state.jobName + " added")
    }

    handleDateChange = date => {
        this.setState({
            launchDate: date
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

    employeeTitleFields = [
        {
            id: 'BLDR',
            name: 'Builder'
        },
        {
            id: 'DSIGNR',
            name: 'Designer'
        },
        {
            id: 'NRSE',
            name: 'Nurse'
        },
        {
            id: 'ELCTRN',
            name: 'Electrician'
        },
        {
            id: 'MNUFCTR',
            name: 'Manufacturer'
        }
    ]

    jobNameFields = [
        {
            id: 'ELECENGG',
            name: 'Electrical Engineer'
        },
        {
            id: 'CIVLENGG',
            name: 'CiviL Engineer'
        },
        {
            id: 'PLMBR',
            name: 'Plumber'
        },
        {
            id: 'SURGN',
            name: 'Surgeon'
        },
        {
            id: 'PNTR',
            name: 'Painter'
        },
    ]

    render() {
        return (
            <div>
                <h2>Create Mission</h2>
                <form>
                    <label>Mission Name: </label>
                    <input
                        name="missionName"
                        placeholder="Enter Mission Name"
                        value={this.state.missionName}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Mission Description: </label>
                    <input
                        name="missionDescription"
                        placeholder="Enter Mission Description"
                        value={this.state.missionDescription}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Country of Origin: </label>
                    <select value={this.state.countryOrigin} onChange={e => this.handleCountryOriginSelect(e)}>
                        <option disabled={true} value="">Select Country</option>
                        {this.countryOptions.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <br />
                    <label>Allowed Countries: </label>
                    <Multiselect
                        placeholder="Select Allowed Countries"
                        options={this.countryOptions}
                        selectedValues={this.state.countryAllowed}
                        displayValue="name"
                        onSelect={this.handleMultiSelect}
                    />
                    <br />
                    <label>Coordinator Name: </label>
                    <input
                        name="coordinatorName"
                        placeholder="Enter Mission Description"
                        value={this.state.coordinatorName}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Coordinator Contact Info: </label>
                    <input
                        name="coordinatorContact"
                        placeholder="Enter Mission Description"
                        value={this.state.coordinatorContact}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Location: </label>
                    <input
                        name="location"
                        placeholder="Enter Mission Description"
                        value={this.state.location}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Mission Status: </label>
                    <select value={this.state.missionStatus} onChange={e => this.handleMissionStatusSelect(e)}>
                        <option disabled={true} value="">Select Mission</option>
                        {this.missionStatusFields.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <br />
                    <label>Mission Duration (in months): </label>
                    <input
                        name="duration"
                        type="number"
                        defaultValue='0'
                        onChange={this.onNumberInput} />
                    <br />
                    <label>Launch Date</label>
                    <DatePicker
                        selected={this.state.launchDate}
                        onChange={this.handleDateChange}
                    />
                    <br />
                    <label>Cargo For Journey: </label>
                    <input type="file" name="cargoForJourney" onChange={this.handleOnFileChange} />
                    <a href="#" download="file.txt">Download Template</a>
                    <br />
                    <label>Cargo For Mission: </label>
                    <input type="file" name="cargoForMission" onChange={this.handleOnFileChange} />
                    <a href="#" download="file.txt">Download Template</a>
                    <br />
                    <label>Cargo For Journey: </label>
                    <input type="file" name="cargoForOtherMission" onChange={this.handleOnFileChange} />
                    <a href="#" download="file.txt">Download Template</a>
                    <br />
                    <label>Employee Title: </label>
                    <select value={this.state.employeeTitle} onChange={e => this.handleEmployeeTitleSelect(e)}>
                        <option disabled={true} value="">Select Employee Title</option>
                        {this.employeeTitleFields.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <label>Number of Employee: </label>
                    <input
                        name="noOfEmployee"
                        type="number"
                        defaultValue='0'
                        onChange={this.onNumberInput} />
                    <button onClick={this.handleAddEmployment}>Add Employment</button>
                    <br />
                    <label>Job Name: </label>
                    <select value={this.state.jobName} onChange={e => this.handleJobNameSelect(e)}>
                        <option disabled={true} value="">Select Job Name</option>
                        {this.jobNameFields.map(o => <option key={o.id} value={o.name}>{o.name}</option>)}
                    </select>
                    <label>Job Description: </label>
                    <input
                        name="jobDescription"
                        placeholder="Enter Job Description"
                        value={this.state.jobDescription}
                        onChange={e => this.handleInputChange(e)} />
                    <button onClick={this.handleAddJob}>Add Job</button>
                    <br />
                    <button onClick={this.handleSubmit}>Submit</button>
                </form>
            </div>
        )
    }
}
