import axios from "axios";
 const DEPARTMENT_REST_API_BASE_URL='http://localhost:8080/api/departments' ;

 export const getlistDpartments = () =>{
    return axios.get(DEPARTMENT_REST_API_BASE_URL)
 }

 export const createDepartment= (department) => axios.post(DEPARTMENT_REST_API_BASE_URL + '/create', department);

export const getDepartmentById = (departemntId) => axios.get(DEPARTMENT_REST_API_BASE_URL + '/department/' + departemntId);

 export const updateDepartment = (departmentId, department) => axios.put(DEPARTMENT_REST_API_BASE_URL + '/department/' +departmentId + '/update', department);

export const deleteDepartment = (departmentId) => axios.delete(DEPARTMENT_REST_API_BASE_URL + '/department/' +departmentId + '/delete');