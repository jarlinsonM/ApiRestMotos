import MotosRow from "./MotosRow";


function TableMotos({motos, onEdit, onDelete}){

    return(

        <table>
            <thead>
                <tr>
                    <th>MARCA</th>
                    <th>MODELO</th>
                    <th>CILINDRAJE</th>
                    <th>PLACA</th>
                </tr>
            </thead>
            <tbody>
                {motos.map((moto)=> (
                    <MotosRow 
                    key= {moto.id} 
                    moto={moto} 
                    onEdit={onEdit} 
                    onDelete={onDelete}/>

                ))}
            </tbody>
        </table>
    )
}
export default TableMotos