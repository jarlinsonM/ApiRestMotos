function DetallesRow({detalle, onEdit, onDelete}){
    const handleEdit2 = ()=>{
        onEdit(detalle)
    }

    const handleDelete2 = ()=>{
        onDelete(detalle.id_detalle)
    } 

    return(
        <tr>
            <td>{detalle.referencia}</td>
            <td>{detalle.id_moto}</td>
            <td>{detalle.color}</td>
            <td>{detalle.precio}</td>
            <td>
                <button onClick={handleEdit2}>Edit ✍️ </button>
                <button onClick={handleDelete2}>Delete 🗑️</button>
            </td>
        </tr>

    )
    

}
export default DetallesRow