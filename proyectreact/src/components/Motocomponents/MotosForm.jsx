import { useState } from "react"
function MotosForm ({onSubmit}){

    const [marca, setMarca] = useState('')
    const [modelo, setModelo] = useState('')
    const[cilindraje, setCilindraje]= useState('')
    const [placa, setPlaca] = useState('')
    

    const handleMarcaChange = (event) => {
        setMarca(event.target.value)
    }

    const handleModeloChange = (event) => {
        setModelo(event.target.value)
    }
    
    const handleCilindrajeChange = (event) => {
        setCilindraje(event.target.value)
    }
    
    const handlePlacaChange = (event) => {
        setPlaca(event.target.value)
    }

    //funcion para enviar el formulario
    const handleSubmit = (event) => {
        event.preventDefault()
        onSubmit({marca, modelo,cilindraje, placa})
        setMarca('')
        setModelo('')
        setPlaca('')
    }

    return(
        < form onSubmit={handleSubmit}> 
            <input type= "text" placeholder = "Marca" value={marca} onChange= {handleMarcaChange} required />
            <input type= "text" placeholder = "Modelo" value={modelo} onChange= {handleModeloChange} required />
            <input type= "text" placeholder = "Cilindraje" value={modelo} onChange= {handleCilindrajeChange} required />
            <input type= "text" placeholder = "placa" value={placa} onChange= {handlePlacaChange} required />
            <br/>
            <button type ="Submit">Guardar</button>
        </form>
        

    )

}
export default MotosForm