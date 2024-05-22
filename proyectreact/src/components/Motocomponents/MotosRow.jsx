
function MotosRow({moto, onEdit, onDelete}){
    const handleEdit = () =>{
        onEdit(moto)
    }
    const handleDelete = () =>{
        onDelete(moto.id)
    }

    return(
        <tr>
            <td>{moto.marca}</td> 
            <td>{moto.modelo}</td>
            <td>{moto.cilindraje}</td>
            <td>{moto.placa}</td>
            <td>
                <button onClick = {handleEdit}>edit ✍️</button>
                <button onClick = {handleDelete}>delete 🗑️</button>
                
            </td> 
        </tr>
    )

}
export default MotosRow