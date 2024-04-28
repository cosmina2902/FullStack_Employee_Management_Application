import React, { useEffect, useState } from 'react'
import { deleteDepartment, getlistDpartments } from '../services/DepartmentsService';
import { Link, useNavigate, useParams } from 'react-router-dom';

export const ListDepartmentComponent = () => {

   

    const [departments, setDepartmemts] = useState([]);

    useEffect(  () =>{
        listOfAllDepartments();
    }, [])

    const navigator = useNavigate();
    function updateDepartment(id){
        navigator(`/edit-department/${id}`)
    }

    function listOfAllDepartments(){
        getlistDpartments().then((response)=>{
            console.log(response.data);
            setDepartmemts(response.data)
        }).catch(error => {
            console.error(error)
        })

    }

    function removeDepartment(id){

        deleteDepartment(id).then((response)=>{
           listOfAllDepartments();
        }).catch(error=>{
            console.error(error);
        })
    }
  return (
    <div className='container'>
        <h2 className='text-center'>List of Deprtments</h2>
        <Link to='/add-department' className='btn btn-primary mb-2'>Add Department</Link>
        <table className='table table-striped table-bordered'>
            <thead>
                <tr>
                    <th>Department Id</th>
                    <th>Department Name</th>
                    <th>Department Description</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                {
                    departments.map(department => 
                    <tr key={department.id}> 
                            <td>{department.id}</td>
                            <td>{department.departmentName}</td>
                            <td>{department.departmentDescription}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateDepartment(department.id)}>Update</button>
                                <button className='btn btn-danger ' onClick={() => removeDepartment(department.id)} 
                                style={{marginLeft: '10px'}}
                                >Delete</button>
                            </td>
                    </tr>)
                }
            </tbody>
        </table>
    </div>
  )
}

export default ListDepartmentComponent