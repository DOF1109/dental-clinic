window.addEventListener('load', function () {

const url = '/paciente/todos';
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        // Recorremos la colección de pacientes del JSON
        for(paciente of data){
            // Por cada paciente armaremos una fila de la tabla
            // Cada fila tendrá un id que nos permitirá actualizar/borrar la fila
            let tableBody = document.getElementById("pacienteTableBody");
            let pacienteRow = tableBody.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;
            pacienteRow.classList.add("align-middle");

            // Creamos un boton delete para poder eliminar la fila
            // Este invoca a deleteBy que llama a la API para eliminar un paciente
            let deleteButton = `
                <button id='btn_delete_${paciente.id}' type="button"
                    onclick='deleteBy("${paciente.id}")' class="btn btn-danger btn_delete">
                    &times</button>`;

            // Creamos un boton que muestra el id para poder editar la fila
            // Este invoca a findBy que busca el paciente que queremos
            // modificar y muestra los datos del mismo en un formulario
            let updateButton = `
                <button id='btn_id_${paciente.id}' type="button"
                    onclick='findBy("${paciente.id}")' class="btn btn-primary btn_id">
                    ${paciente.id}</button>`;

            // Armamos cada columna de la fila
            pacienteRow.innerHTML = `
                <td>${updateButton}</td>
                <td class='td_nombre'>${paciente.nombre.toUpperCase()}</td>
                <td class='td_apellido'>${paciente.apellido.toUpperCase()}</td>
                <td class='td_cedula'>${paciente.cedula.toUpperCase()}</td>
                <td class='td_fechaIngreso'>${paciente.fechaIngreso.toUpperCase()}</td>
                <td class='td_calle'>${paciente.domicilio.calle.toUpperCase()}</td>
                <td class='td_numero'>${paciente.domicilio.numero}</td>
                <td class='td_localidad'>${paciente.domicilio.localidad.toUpperCase()}</td>
                <td class='td_provincia'>${paciente.domicilio.provincia.toUpperCase()}</td>
                <td class='td_email'>${paciente.email.toUpperCase()}</td>
                <td>${deleteButton}</td>`;

        };
    })

})

function insertEditModal(){
    const editModal = document.createElement('div');
    editModal.innerHTML = `
    <div class="modal fade" id="pacienteEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="pacienteEditModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
        <div class="modal-content bg-body-tertiary">
            <div class="modal-header">
                <h3 class="modal-title fs-4" id="pacienteEditModalLabel">Modificar datos del paciente</h3>
            </div>
            <div class="modal-body px-4">
                <form id="update_paciente_form">
                    <div class="form-group mb-3">
                        <label>Id:</label>
                        <input type="text" class="form-control" id="paciente_id" readonly>
                    </div>
                    <div class="form-group my-3">
                        <label>Nombre:</label>
                        <input type="text" class="form-control" placeholder="Ingrese el nombre" id="nombre" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Apellido:</label>
                        <input type="text" class="form-control" placeholder="Ingrese el apellido" id="apellido" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Cedula:</label>
                        <input type="text" class="form-control" placeholder="Ingrese N° de cedula" id="cedula" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Fecha de ingreso:</label>
                        <input type="text" class="form-control" placeholder="Fecha de ingreso" id="fechaIngreso" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Calle:</label>
                        <input type="text" class="form-control" placeholder="Ingrese la calle" id="calle" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Número:</label>
                        <input type="text" class="form-control" placeholder="Ingrese el número" id="numero" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Localidad:</label>
                        <input type="text" class="form-control" placeholder="Ingrese la direccion" id="localidad" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Provincia:</label>
                        <input type="text" class="form-control" placeholder="Ingrese la provincia" id="provincia" required>
                    </div>
                    <div class="form-group my-3">
                        <label>Email:</label>
                        <input type="text" class="form-control" placeholder="Ingrese el email" id="email" required>
                    </div>
                </form>
            </div> 
            <div class="modal-footer border-0 px-4 py-3">
                <button id="btnCancelUpdateModal" type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                <button id="btnConfirmUpdateModal" type="submit" form="update_paciente_form" class="btn btn-primary">Guardar cambios</button>
            </div>
        </div>
    </div>
</div>`;

    document.body.appendChild(editModal);
}

function insertDeleteModal(){
    const deleteModal = document.createElement('div');
    deleteModal.innerHTML = `
        <div class="modal fade" id="pacienteDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="pacienteDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content bg-body-tertiary">
                    <div class="modal-header border-0">
                        <h3 class="modal-title fs-5" id="pacienteDeleteModalLabel">¿Seguro que desea eliminar el paciente?</h3>
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