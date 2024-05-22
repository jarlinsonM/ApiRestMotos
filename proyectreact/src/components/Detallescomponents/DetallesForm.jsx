import { useState } from "react"
function DetallesForm ({onSubmit}){

    const [referncia, setReferencia] = useState('')
    const [id_moto, setId_moto] = useState('')
    const [color, setColor] = useState('')
    const [precio, setPrecio] = useState('')

    const handleRefernciaChange = (event) => {
        setReferencia(event.target.value)
    }

    const handleId_motoChange = (event) => {
        setId_moto(event.target.value)
    }

    const handleColorChange= (event) =>{
        setColor(event.target.value)
    }

    const handlePrecioCjange = (event) => {
        setPrecio(event.target.value)
    }

    //funcion para enviar el formulario
    const handleSubmit = (event) => {
        event.preventDefault()
        onSubmit({referncia, id_moto,cilindraje,color,precio})
        setMarca('')
        setModelo('')
        setPlaca('')
    }

    return(
        < form onSubmit={handleSubmit}> 
            <input type= "text" placeholder = "Referencia" value={referncia} onChange= {handleRefernciaChange} required />
            <input type= "text" placeholder = "Id_moto" value={id_moto} onChange= {handleId_motoChange} required />
            <input type= "text" placeholder = "Color" value={color} onChange= {handleColorChange} required />
            <input type= "text" placeholder = "precio" value={precio} onChange= {handlePrecioCjange} required />
            <br/>
            <button type ="Submit">Guardar</button>
        </form>
        

    )

}
export default DetallesForm