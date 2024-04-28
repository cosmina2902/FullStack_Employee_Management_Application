import React, { useEffect, useState } from 'react'
import { createDepartment, getDepartmentById, updateDepartment } from '../services/DepartmentsService';
import { useNavigate, useParams } from 'react-router-dom';

export const DepartmentsComponent = () => {

    const[departmentName, SetDepartmentName] = useState('')
    const[departmentDescription, setDepartmentDescription] = useState('')

    const navigator = useNavigate()
    const {id} = useParams();

    function pageTile(){
        if(id){
            return <h2 className='text-center'>Update Department</h2>
        }
        else{
            return <h2 className='text-center'>Add Department</h2>
        }
    }

    useEffect(()=>{

        getDepartmentById(id).then((response)=>{
            SetDepartmentName(response.data.departmentName);
            setDepartmentDescription(response.data.departmentDescription);
        }).catch(error=>{
            console.error(error)
        })

    },[id])

    function saveOrUpdateDepartment(e){
        e.preventDefault();

        const department = {departmentName, departmentDescription}


        if(id){
            updateDepartment(id, department).then((response)=>{
                navigator('/departments')
            }).catch(error=>{
                console.error(error);
            })
        }

        createDepartment(department).then((response)=>{
            navigator('/departments');
        }).catch(error=>{
            console.error(error)
        })
    }

  return (
    <div className='container'>
        <br/> <br />
        <div className='row'>
            <div className='card col-md-6 offset-md-3 offset-md-3'>
                {
                    pageTile()

                }
                
                <div className='card-body'>
                    <form onSubmit={saveOrUpdateDepartment}>
                        <div className='form-group mb-2'>
                            <label className='form-label'>Department Name</label>
                            <input 
                            type='text' 
                            placeholder='Department Name' 
                            name='departmentName' 
                            value={departmentName} 
                            className='form-control'
                            // className={`form-control ${errors.firstName? 'is-invalid': '' }`}
                            onChange={(e) => SetDepartmentName(e.target.value)}
                            >
                            </input>

                            {/* {errors.firstName &&  <div className='invalid-feedback'> {errors.firstName}</div> }  */}

                        </div>

                        <div className='form-group mb-2'>
                            <label className='form-label'>Department Description</label>
                            <input 
                            type='text' 
                            placeholder='Enter Department Description' 
                            name='departmentDescription' 
                            value={departmentDescription} 
                            className='form-control'
                            // className={`form-control ${errors.lastName? 'is-invalid': '' }`}
                            onChange={(e) => setDepartmentDescription(e.target.value)}
                            >
                            </input>

                            {/* {errors.lastName &&  <div className='invalid-feedback'> {errors.lastName}</div> }  */}

                        </div>                        
                        <button type= 'submit' className='btn btn-success mb-2'>Submit</button>
                    </form>

                </div>

            </div>

        </div>
    </div>
  )
}
