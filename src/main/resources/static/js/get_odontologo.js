window.addEventListener('load', function () {
    
    const url = '/odontologo/todos';
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        // Recorremos la colección de odontologos del JSON
        for(odontologo of data){
            // Por cada odontologo armaremos una fila de la tabla
            // Cada fila tendrá un id que nos permitirá actualizar/borrar la fila
            let table = document.getElementById("odontologoTable");
            let odontologoRow = table.insertRow();
            let tr_id = 'tr_' + odontologo.id;
            odontologoRow.id = tr_id;
            odontologoRow.classList.add("align-middle");

            // Creamos un boton delete para poder eliminar la fila
            // Este invoca a deleteBy que llama a la API para eliminar un odontologo
            let deleteButton = `
                <button id='btn_delete_${odontologo.id}' type="button" 
                    onclick='deleteBy("${odontologo.id}")' class="btn btn-danger btn_delete">
                    &times</button>`;

            // Creamos un boton que muestra el id para poder editar la fila
            // Este invoca a findBy que busca el odontologo que queremos
            // modificar y muestra los datos del mismo en un formulario
            let updateButton = `
                <button id='btn_id_${odontologo.id}' type="button" 
                    onclick='findBy("${odontologo.id}")' class="btn btn-primary btn_id"> 
                    ${odontologo.id}</button>`;

            // Armamos cada columna de la fila
            odontologoRow.innerHTML = `
                <td>${updateButton}</td>
                <td class='td_matricula'>${odontologo.matricula.toUpperCase()}</td>
                <td class='td_nombre'>${odontologo.nombre.toUpperCase()}</td>
                <td class='td_apellido'>${odontologo.apellido.toUpperCase()}</td>
                <td>${deleteButton}</td>`;

        };
    })

})

function insertEditModal(){
    const editModal = document.createElement('div');
    editModal.innerHTML = `
        <div class="modal fade" id="odontologoEditModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="odontologoEditModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
                <div class="modal-content bg-body-tertiary">
                    <div class="modal-header">
                        <h3 class="modal-title fs-4" id="odontologoEditModalLabel">Modificar datos del odontologo</h3>
                    </div>
                    <div class="modal-body px-4">
                        <form id="update_odontologo_form">
                            <div class="form-group mb-3">
                                <label>Id:</label>
                                <input type="text" class="form-control" id="odontologo_id" readonly>
                            </div>
                            <div class="form-group my-3">
                                <label>Matricula:</label>
                                <input type="text" class="form-control" placeholder="Ingrese la matricula" id="matricula" required>
                            </div>
                            <div class="form-group my-3">
                                <label>Nombre:</label>
                                <input type="text" class="form-control" placeholder="Ingrese el nombre" id="nombre" required>
                            </div>
                            <div class="form-group my-3">
                                <label>Apellido:</label>
                                <input type="text" class="form-control" placeholder="Ingrese el apellido" id="apellido" required>
                            </div>
                        </form>
                    </div> 
                    <div class="modal-footer border-0 px-0 py-3">
                        <button id="btnCancelUpdateModal" type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                        <button id="btnConfirmUpdateModal" type="submit" form="update_odontologo_form" class="btn btn-primary">Guardar cambios</button>
                    </div>
                </div>
            </div>
        </div>`;

    document.body.appendChild(editModal);
}

function insertDeleteModal(){
    const deleteModal = document.createElement('div');
    deleteModal.innerHTML = `
        <div class="modal fade" id="odontologoDeleteModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="odontologoDeleteModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content bg-body-tertiary">
                    <div class="modal-header border-0">
                        <h3 class="modal-title fs-5" id="odontologoDeleteModalLabel">¿Seguro que desea eliminar el odontologo?</h3>
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