import axios from "axios";
 const REST_API_BASE_URL='http://localhost:8080/api/employees';

 export const listEmployees = () =>{
    return axios.get(REST_API_BASE_URL)
 }

 export const creatEmployee= (employee) => axios.post(`${REST_API_BASE_URL}/create`, employee);

 export const getEmployee = (employeeId) => axios.get(REST_API_BASE_URL + '/employee/' + employeeId);

 export const updateEmployee = (employeeId, employee) => axios.put(REST_API_BASE_URL + '/employee/' +employeeId + '/updated', employee);

export const deleteEmployee = (employeeId) => axios.delete(REST_API_BASE_URL + '/employee/' +employeeId + '/deleted');