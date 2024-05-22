import { useState,useEffect  } from 'react'
import axios from 'axios'
import './App.css'
import MotosForm from './components/Motocomponents/MotosForm'
import TableMotos from './components/Motocomponents/TableMotos'
import DetallesTable from './components/Detallescomponents/DetallesTable'
import DetallesForm from './components/Detallescomponents/DetallesForm'


function App() {
  const [motos, setMotos] = useState([])
  const [editingMoto, setEditingMoto] = useState(null) 
  const[detalles, setDetalles]= useState([])
  const [editingDetalle, setEditingDetalle] = useState(null)

  //actualiza la lista de motos por cada moto nueva
  useEffect(()=> {
    fetchMotos()
  },[])

  //recorre la lista de motos y las retorna como respuesta
  const fetchMotos = async() =>{
    try {
      const response = await axios.get(`http://localhost:8080/api/motos`)
      setMotos(response.data)
    } catch (error) {
      console.log('Error al cargar las motos : ', error)
    }
  }

  //crea o actualiza una moto
  const handCreateOrUpdateMoto = async(motoData)=>{
    if(editingMoto){
      await axios.put(`http://localhost:8080/api/motos/${editingMoto.id}`,motoData)
    }else{
      await axios.post(`http://localhost:8080/api/motos`,motoData)
    }
    fetchMotos()
    setEditingMoto(null)
  }
 
  //permite editar 
  const handleEditMoto= (moto) =>{
    setEditingMoto(moto)
  }
 
  //permite eliminar apartir de una id
  const handleDeleteMoto = async(motoId)=>{
    await axios.delete(`http://localhost:8080/api/motos/${motoId}`)
    fetchMotos()
    
 
  }
  useEffect(()=> {
    fetchDetalles()
  },[])
  //recorre la lista de detalles 
  const fetchDetalles = async() =>{
    try {
      const response = await axios.get(`http://localhost:8080/api/motos/detalles`)
      setDetalles(response.data)
    } catch (error) {
      console.log('Error al cargar las motos : ', error)
    }
  }


  //permite editar los detalles
  const handleEditDetalle= (detalle) =>{
    setEditingDetalle(detalle)
  }
 
  //permite eliminar apartir de una id
  const handleDeleteDetalle = async(detallesId)=>{
    await axios.delete(`http://localhost:8080/api/motos/detalles${detallesId}`)
    fetchDetalles()
  }

  //crea o actualiza un detalles
  const hanleCreateOrUpdateDetalle = async(detalleData) =>{
    if(editingDetalle){
      await axios.put(`http://localhost:8080/api/motos/detalles${editingDetalle.id}`,detalleData)
    }else{
      await axios.post(`http://localhost:8080/api/motos/detalles`,detalleData)
    }
    fetchDetalles()
    setEditingDetalle(null)
  }
  

  return (
      <div className='App'>
      <h1> Api Motos</h1>
      <br/>
      <h2>List Motos</h2>
      <TableMotos motos={motos} onEdit={handleEditMoto} onDelete={handleDeleteMoto} />
      <h2>{editingMoto ?  'editar Moto' : 'Crear Moto'}</h2>
      <MotosForm onSubmit={handCreateOrUpdateMoto} initialMoto= {editingMoto} />
      <br/>
      <h2>List Detalles</h2>
        <DetallesTable detalles={detalles} onEdit={handleEditDetalle} onDelete={handleDeleteDetalle} />
        <h2>{editingDetalle ?  'editar Detalle' : 'Crear Detalle'}</h2>
        <DetallesForm onSubmit={hanleCreateOrUpdateDetalle} initialDetalle= {editingDetalle} />
      
      <br/>
    </div> 

  )
}

export default App

