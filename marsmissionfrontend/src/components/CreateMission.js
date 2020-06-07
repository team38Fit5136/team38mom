import React, { Component } from 'react'
import { Multiselect } from 'multiselect-react-dropdown'
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { Redirect } from 'react-router-dom';
import axios from "axios"
import cargoForJourneyTemplate from '../fileTemplates/cargoForJourney.csv'
import cargoForMissionTemplate from '../fileTemplates/cargoForMission.csv'
import cargoForOtherMissionTemplate from '../fileTemplates/cargoForOtherMission.csv'
import moment from 'moment';


export default class CreateMission extends Component {
    constructor(props) {
        super(props)
        this.state = {
            missionName: '',
            missionDescription: '',
            countryOriginID: '',
            countryAllowed: [],
            launchDate: '',
            duration: '',
            coordinator: { id: '', name: '', email: '' },
            coordinatorContact: '',
            missionStatus: '',
            locationID: '',
            cargoForJourney: null,
            cargoForMission: null,
            cargoForOtherMission: null,
            employeeTitle: '',
            noOfEmployee: '',
            employmentRequirements: [],
            jobName: '',
            jobDescription: '',
            jobRequirements: [],
            redirect: false,
            countryList: [],
            missionStatusList: [],
            coordinatorNameList: [],
            locationList: [],
            jobID: null,
            jobIdList: []
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
        this.handleBack = this.handleBack.bind(this)
        this.handleCoordinatorNameChange = this.handleCoordinatorNameChange.bind(this)
        this.handleFileUpload = this.handleFileUpload.bind(this)
        this.handleLocationChange = this.handleLocationChange.bind(this)
        this.removeEmployment = this.removeEmployment.bind(this)
    }

    async componentDidMount() {
        // get list of countries from API call
        axios.get("http://localhost:8083/mom/mission/country")
            .then(res => {
                if (res.data.status === "Success") {
                    this.setState({
                        countryList: res.data.responseMsg
                    })
                    // console.log("countries", res)
                }

            })
            .catch(err => {
                console.log(err)
            })

        // get list of coordinatoes from API call
        axios.get("http://localhost:8081/mom/user/profile?userRole=coordinator")
            .then(res => {
                if (res.data.status === "Success") {
                    // console.log("coordinator", res.data.responseMsg)
                    const json = res.data.responseMsg
                    json.forEach(obj => {
                        // console.log(obj)
                        const tempList = [{ id: obj.user_id, name: obj.full_name, email: obj.user_email }]
                        // console.log("coordinator", tempList)
                        this.setState({
                            coordinatorNameList: tempList
                        })
                    });
                }
            })
            .catch(err => {
                console.log(err)
            })

        // get list of mission status from API call
        axios.get("http://localhost:8083/mom/mission/status")
            .then(res => {
                if (res.data.status === "Success") {
                    // console.log("mission status", res)
                    this.setState({
                        missionStatusList: res.data.responseMsg
                    })
                }
            })
            .catch(err => {
                console.log(err)
            })

        // get list of locations from API call
        axios.get("http://localhost:8083/mom/mission/location")
            .then(res => {
                if (res.data.status === "Success") {
                    console.log("location", res)
                    this.setState({
                        locationList: res.data.responseMsg
                    })
                }
            })
            .catch(err => {
                console.log(err)
            })
    }

    handleInputChange = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();     // prevents default behavior of putting state values in URL
        // fetch("../fileTemplates/cargoForOtherMission.csv")
        //     .then(res => {
        //         console.log(res)
        //     })
        // console.log(data)

        let forJourney = false
        let forMission = false
        let forOtherMission = false
        let missionName = false
        let missionDesc = false
        let coordinator = false
        let launchDate = false

        if (this.state.missionName === "") {
            alert("Mission Name is mandatory")
        } else {
            missionName = true
        }

        if (this.state.missionDescription === "") {
            alert("Mission description is mandatory")
        } else {
            missionDesc = true
        }

        if (this.state.coordinatorID === "") {
            alert("Coordinator is mandatory")
        } else {
            coordinator = true
        }

        if (this.state.launchDate === "") {
            alert("Launch Date is mandatory")
        } else {
            launchDate = true
        }

        if (this.state.cargoForJourney !== null && this.state.cargoForJourney.name !== 'cargoForJourney.csv') {
            alert("Filename for Cargo For Journey must be cargoForJourney.csv. Please use correct file name.")
        } else if (this.state.cargoForJourney === null) {
            alert("Cargo For Journey is mandatory")
        } else {
            forJourney = true
        }

        if (this.state.cargoForMission !== null && this.state.cargoForMission.name !== 'cargoForMission.csv') {
            alert("Filename for Cargo For Mission must be cargoForMission.csv. Please use correct file name.")
        } else if (this.state.cargoForMission === null) {
            alert("Cargo for mission is mandatory")
        } else {
            forMission = true
        }

        if (this.state.cargoForOtherMission !== null && this.state.cargoForOtherMission.name !== 'cargoForOtherMission.csv') {
            alert("Filename for Cargo For Other Mission must be cargoForOtherMission.csv. Please use correct file name.")
        } else if (this.state.cargoForOtherMission === null) {
            alert("Cargo for other mission is mandatory")
        } else {
            forOtherMission = true
        }

        if (forJourney && forMission && forOtherMission && missionName && missionDesc && coordinator && launchDate) {
            console.log("State", this.state)
            const propss = {
                "missionName": this.state.missionName,
                "missionDetails": this.state.missionDescription,
                "countryAllowed": this.state.countryAllowed.map(o => o.country_id),
                "location": this.state.locationID,
                "jobID": this.state.jobIdList,
                "statusID": this.state.missionStatus,
                "countryOrigin": this.state.countryOriginID,
                "launchDate": moment(this.state.launchDate).format("YYYY-MM-DD"),
                "duration": this.state.duration,
                "coordinatorID": this.state.coordinator.id,
            }
            // POST request for Mission microservice
            const formData = new FormData()
            formData.append("cargoForJourney", this.state.cargoForJourney)
            formData.append("cargoForMission", this.state.cargoForMission)
            formData.append("cargoForOtherMission", this.state.cargoForOtherMission)
            formData.append("props", JSON.stringify(propss))
            axios.post("http://localhost:8082/mom/mission/",
                formData
            )
                .then(response => {
                    console.log(response)
                    if (response.data.status === "success") {
                        alert("Mission successfully created, going back to home page...")
                        this.setState({ redirect: true })
                    }
                })
                .catch(err => {
                    console.log(err)
                })
        }

    }

    handleCountryOriginSelect = (e) => {
        this.setState({
            countryOriginID: e.target.value

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
        if (this.state.employeeTitle === '' || this.state.noOfEmployee === '') {
            alert("No employees entered, nothing added")
        } else {
            let object = {
                'empTitle': this.state.employeeTitle,
                'numberOfEmp': this.state.noOfEmployee
            }
            this.setState(prevState => {
                let employmentRequirements = prevState.employmentRequirements.concat(object)
                return {
                    employmentRequirements
                }
            })
            alert(this.state.noOfEmployee + " " + this.state.employeeTitle + " added")
            this.state.employmentRequirements.map(function (obj, index) {
                // console.log(obj)
                // console.log(index)
            })
        }
    }

    removeEmployment = (index, e) => {
        e.preventDefault()
        // console.log(index)
        const empReq = [...this.state.employmentRequirements]
        empReq.splice(index, 1)
        this.setState({
            employmentRequirements: empReq
        })
        // console.log(this.state.employmentRequirements)
    }

    handleJobNameSelect = (e) => {
        this.setState({
            jobName: e.target.value
        })
    }

    handleAddJob = (e) => {
        e.preventDefault()
        if (this.state.jobName === '' || this.state.jobDescription === '') {
            alert("Job title or job description cannot be empty")
        } else {
            e.preventDefault()
            let object = {
                'jobName': this.state.jobName,
                'jobDesc': this.state.jobDescription
            }
            this.setState(prevState => {
                let jobRequirements = prevState.jobRequirements.concat(object)
                return {
                    jobRequirements
                }
            })

            // send POST request
            axios({
                method: "POST",
                url: "http://localhost:8083/mom/mission/job",
                data: {
                    "jobName": this.state.jobName,
                    "jobDesc": this.state.jobDescription,
                    "employment": this.state.employmentRequirements
                }
            })
                .then(res => {
                    if (res.data.status === 'success') {
                        const json = res.data
                        console.log("Job POST response: ", json)
                        this.setState({
                            jobID: res.data.jobID,
                            employmentRequirements: []
                        })
                        this.setState(prevState => {
                            let jobIdList = prevState.jobIdList.concat(this.state.jobID)
                            return {
                                jobIdList
                            }
                        })
                        alert("Job added with job ID: " + this.state.jobID + ". You may add another job with required employment.")
                    }
                })
                .catch(err => {
                    console.log(err)
                })
            // alert(this.state.jobName + " added")
        }
    }

    handleDateChange = date => {
        this.setState({
            launchDate: date
        })
    }

    handleBack = () => {
        this.setState({
            redirect: true
        })
    }

    handleCoordinatorNameChange = (e) => {
        const selectedIndex = e.target.selectedIndex
        this.setState({
            coordinator: {
                id: e.target.options[selectedIndex].value,
                name: e.target.options[selectedIndex].getAttribute("name"),
                email: e.target.options[selectedIndex].getAttribute("email")
            }
        })
    }

    handleFileUpload = (e) => {
        e.preventDefault()
        if (this.state.cargoForJourney !== null) {
            const formData = new FormData()
            formData.append(
                "cargoForJourney",
                this.state.cargoForJourney,
                this.state.cargoForJourney.name
            )
            console.log(this.state.cargoForJourney)
            console.log(formData.get("cargoForJourney"))
            axios.post("http://localhost:8000/upload",
                formData
            )
        } else {
            alert("No file selected, please select a file")
        }

    }

    handleLocationChange = (e) => {
        this.setState({
            locationID: e.target.value
        })
    }

    render() {
        if (this.state.redirect) {
            return <Redirect push to="/navigation" />
        }

        return (
            <div>
                <h4 className="m-3 d-flex justify-content-center">Create Mission</h4>
                <form>
                    <label>Mission Name: </label>
                    <input
                        name="missionName"
                        placeholder="Enter Mission Name"
                        value={this.state.missionName}
                        onChange={e => this.handleInputChange(e)}
                        required={true} />
                    <br />
                    <label>Mission Description: </label>
                    <input
                        name="missionDescription"
                        placeholder="Enter Mission Description"
                        value={this.state.missionDescription}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Country of Origin: </label>
                    <select value={this.state.countryOriginID} onChange={e => this.handleCountryOriginSelect(e)}>
                        <option disabled={true} value="">Select Country</option>
                        {this.state.countryList.map(o => <option key={o.country_id} value={o.country_id}>{o.country_name}</option>)}
                    </select>
                    <br />
                    <label>Allowed Countries: </label>
                    <Multiselect
                        placeholder="Select Allowed Countries"
                        options={this.state.countryList}
                        selectedValues={this.state.countryAllowed}
                        displayValue="country_name"
                        onSelect={this.handleMultiSelect}
                    />
                    <br />
                    <label>Coordinator Name: </label>
                    <select value={this.state.coordinator.name} onChange={e => this.handleCoordinatorNameChange(e)}>
                        <option disabled={true} value="">Select Coordinator</option>
                        {this.state.coordinatorNameList.map(o => <option key={o.id} value={o.id} email={o.email} name={o.name}>{o.name}</option>)}
                    </select>
                    <br />
                    <label>Coordinator Contact Info: </label>
                    <input
                        name="coordinatorContact"
                        placeholder="Enter contact info"
                        value={this.state.coordinator.email}
                        onChange={e => this.handleInputChange(e)} />
                    <br />
                    <label>Location: </label>
                    <select value={this.state.locationID} onChange={this.handleLocationChange}>
                        <option disabled={true} value="">Select Location</option>
                        {this.state.locationList.map(o => <option key={o.location_id} value={o.location_id}>{o.location_north + " N" + ", " + o.location_east + " E"}</option>)}
                    </select>
                    <br />
                    <label>Mission Status: </label>
                    <select value={this.state.missionStatus} onChange={e => this.handleMissionStatusSelect(e)}>
                        <option disabled={true} value="">Select Mission</option>
                        {this.state.missionStatusList.map(o => <option key={o.status_id} value={o.status_id}>{o.status_name}</option>)}
                    </select>
                    <br />
                    <label>Mission Duration (in months): </label>
                    <input
                        name="duration"
                        type="number"
                        defaultValue='0'
                        min={0}
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
                    <a href={cargoForJourneyTemplate} download="cargoForJourney.csv">Download Template</a>
                    {/* <button onClick={this.handleFileUpload}>Upload</button> */}
                    <br />
                    <label>Cargo For Mission: </label>
                    <input type="file" name="cargoForMission" onChange={this.handleOnFileChange} />
                    <a href={cargoForMissionTemplate} download="cargoForMission.csv">Download Template</a>
                    <br />
                    <label>Cargo For Other Mission: </label>
                    <input type="file" name="cargoForOtherMission" onChange={this.handleOnFileChange} />
                    <a href={cargoForOtherMissionTemplate} download="cargoForOtherMission.csv">Download Template</a>
                    <br />
                    <label>Job Name: </label>
                    <input
                        name="jobName"
                        placeholder="Enter job name"
                        value={this.state.jobName}
                        onChange={e => this.handleInputChange(e)} />
                    <label>Job Description: </label>
                    <input
                        name="jobDescription"
                        placeholder="Enter Job Description"
                        value={this.state.jobDescription}
                        onChange={e => this.handleInputChange(e)} />
                    <button onClick={this.handleAddJob}>Add Job</button>
                    <br />
                    <label>Employee Title: </label>
                    <input
                        name="employeeTitle"
                        placeholder="Enter job title"
                        value={this.state.employeeTitle}
                        onChange={e => this.handleInputChange(e)} />
                    <label>Number of Employee: </label>
                    <input
                        name="noOfEmployee"
                        type="number"
                        defaultValue='0'
                        min={0}
                        onChange={this.onNumberInput} />
                    <button onClick={this.handleAddEmployment}>Add Employment to Job</button>
                    {this.state.employmentRequirements.map((obj, index) => {
                        return (
                            <div key={index}>
                                <input value={obj.numberOfEmp + " " + obj.empTitle} />
                                <button onClick={(e) => this.removeEmployment(index, e)}>Remove Employment</button>
                            </div>
                        )
                    })}
                    <br />
                    <button onClick={this.handleSubmit} style={{ marginRight: "10px" }}>Submit</button>
                    <button onClick={this.handleBack}>Back</button>
                </form>
            </div>
        )
    }
}