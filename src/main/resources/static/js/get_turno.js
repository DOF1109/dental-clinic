window.addEventListener('load', function () {
    
    const url = '/turno/todos';
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        // Recorremos la colección de turnos del JSON
        for(turno of data){
            // Por cada turno armaremos una fila de la tabla
            // Cada fila tendrá un id que nos permitirá actualizar/borrar la fila
            let tableBody = document.getElementById("turnoTableBody");
            let turnoRow = tableBody.insertRow();
            let tr_id = 'tr_' + turno.id;
            turnoRow.id = tr_id;
            turnoRow.classList.add("align-middle");

            // Creamos un boton delete para poder eliminar la fila
            // Este invoca a deleteBy que llama a la API para eliminar un turno
            let deleteButton = `
                <button id='btn_delete_${turno.id}' type="button" 
                    onclick='deleteBy("${turno.id}")' class="btn btn-danger btn_delete">
                    &times</button>`;

            // Creamos un boton que muestra el id para poder editar la fila
            // Este invoca a findBy que busca el turno que queremos
            // modificar y muestra los datos del mismo en un formulario
            let updateButton = `
                <button id='btn_id_${turno.id}' type="button" 
                    onclick='findBy("${turno.id}")' class="btn btn-primary btn_id"> 
                    ${turno.id}</button>`;

            // Armamos cada columna de la fila
            turnoRow.innerHTML = `
                <td>${updateButton}</td>
                <td>${turno.odontologoMatricula.toUpperCase()}</td>
                <td>${turno.odontologoNombre.toUpperCase()}</td>
                <td>${turno.odontologoApellido.toUpperCase()}</td>
                <td>${turno.pacienteCedula.toUpperCase()}</td>
                <td>${turno.pacienteNombre.toUpperCase()}</td>
                <td>${turno.pacienteApellido.toUpperCase()}</td>
                <td>${turno.fechaTurno}</td>
                <td>${deleteButton}</td>`;
        };
    })

})

function insertEditModal(){
    const editModal = document.createElement('div');
    editModal.innerHTML = `
        <div class="modal fade" id="turnoEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="turnoEditModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content bg-body-tertiary">
                    <div class="modal-header">
                        <h3 class="modal-title fs-4" id="turnoEditModalLabel">Modificar datos del turno</h3>
                    </div>
                    <div class="modal-body px-4">
                        <form id="update_turno_form">
                            <div class="form-group mb-3">
                                <label>Id:</label>
                                <input type="text" class="form-control" id="turno_id" readonly>
                            </div>
                            <div class="form-group my-3">
                                <label class="control-label pb-2">Paciente:</label>
                                <select id="selectPacientes" class="form-select" aria-label="select paciente" required>
                                    <option disabled value="">Seleccionar paciente</option>
                                </select>
                            </div>
                            <div class="form-group my-3">
                                <label class="control-label pb-2">Odontologo:</label>
                                <select id="selectOdontologos" class="form-select" aria-label="select odontologo" required>
                                    <option disabled value="">Seleccionar odontologo</option>
                                </select>
                            </div>
                            <div class="form-group my-3">
                                <label class="control-label pb-2" for="fechaTurno">Fecha del turno:</label>
                                <input type="date" class="form-control rounded-3" id="fechaTurno"
                                    placeholder="Ingrese la fecha del turno"
                                    name="fechaTurno" required></input>
                            </div>
                        </form>
                    </div> 
                    <div class="modal-footer border-0 px-4 py-3">
                        <button id="btnCancelUpdateModal" type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                        <button id="btnConfirmUpdateModal" type="submit" form="update_turno_form" class="btn btn-primary">Guardar cambios</button>
                    </div>
                </div>
            </div>
        </div>`;

    document.body.appendChild(editModal);
}

function insertDeleteModal(){
    const deleteModal = document.createElement('div');
    deleteModal.innerHTML = `
        <div class="modal fade" id="turnoDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="turnoDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content bg-body-tertiary">
                    <div class="modal-header border-0">
                        <h3 class="modal-title fs-5" id="turnoDeleteModalLabel">¿Seguro que desea eliminar el turno?</h3>
                    </div>
                    <div class="modal-footer border-0">
                        <button id="cancelDeleteModal" type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                        <button id="deleteConfirm" type="button" class="btn btn-primary">Eliminar</button>
                    </div>
                </div>
            </div>
        </div>`;

    document.body.appendChild(deleteModal);
}