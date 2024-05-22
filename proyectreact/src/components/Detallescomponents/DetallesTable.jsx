import DetallesRow from "./DetallesRow";

function DetallesTable({detalles, onEdit, onDelete}){
    return(
        <table>
            <thead>
                <tr>
                    <th>referencia</th>
                    <th>Id_moto</th>
                    <th>Color</th>
                    <th>precio</th>
                </tr>
            </thead>
            <tbody>
                {detalles.map((detalle) => (
                    <DetallesRow
                    key={detalle.id_detalle}
                    detalle={detalle}
                    onEdit= {onEdit}
                    onDelete={onDelete}/>
                ))}

            </tbody>
        </table>
    )
}
export default DetallesTable