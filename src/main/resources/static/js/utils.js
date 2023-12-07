/********** Mostrar u ocultar items segun rol en el menu **********/
function hideMenu() {
    const url = '/usuario';
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        if (data.rol === 'ROLE_USER') {
            this.document.getElementById('pacienteIdLi').classList.add('d-none');
            this.document.getElementById('odontologoIdLi').classList.add('d-none');
        }
    });
}

hideMenu();


/********** Muestra un mensaje toast de éxito o error **********/
// isSucces es true para un menesaje de éxito y false para error
function showToast(message, isSuccess){
    // El color de fondo depende si es exitoso o no el mensaje a mostrar
    const bgToast = (isSuccess)? 'text-bg-success' : 'text-bg-danger';

    const divToast = document.createElement('div');
    divToast.innerHTML = `
        <div class="toast-container position-fixed bottom-0 start-50 translate-middle-x p-4">
            <div id="messageToast" class="toast ${bgToast}" role="alert" aria-live="assertive" aria-atomic="true">
                <div class="toast-header border-0">
                    <strong class="me-auto">${message}</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
            </div>
        </div>`;
    document.body.appendChild(divToast);

    // Creo una instancia del toast y lo muestro
    const messageToast = document.querySelector('#messageToast');
    const toastBootstrap = bootstrap.Toast.getOrCreateInstance(messageToast);
    toastBootstrap.show();
}


/********** Muestra el modal segun el id pasado por parametro **********/
function showModal(idModal, idBtnCancel){
    const htmlModal = document.querySelector(`#${idModal}`);
    const modalBootstrap = bootstrap.Modal.getOrCreateInstance(htmlModal);
    modalBootstrap.show();

    const btnCancelModal = document.querySelector(`#${idBtnCancel}`);
    btnCancelModal.addEventListener('click', () => {
        htmlModal.parentNode.removeChild(htmlModal);

        if (idModal === 'odontologoEditModal') insertEditModal();
        if (idModal === 'odontologoDeleteModal') insertDeleteModal();

        location.reload();
    });
}


/********** Carga y retorna los pacientes de la BBDD en las opciones del select **********/
function cargarSelectPacientes(idSelected) {
    const urlListarPacientes = '/paciente/todos';
    const settings = {
        method: 'GET'
    }

    return fetch(urlListarPacientes, settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        const selectPacientes = document.getElementById("selectPacientes");

        // Recorremos la colección de pacientes del JSON
        for(paciente of data){
            // Por cada paciente armaremos una fila del select
            const pacienteOption = document.createElement("option");
            pacienteOption.value = `${paciente.id};${paciente.nombre};${paciente.apellido};${paciente.cedula}`;
            pacienteOption.text = paciente.cedula + " - " +
                paciente.nombre + " " + 
                paciente.apellido;
                
            if (idSelected == paciente.id) 
                pacienteOption.selected = true;

            selectPacientes.appendChild(pacienteOption);
        };
        // Devuelvo los pacientes cargados en la BBDD
        return data;
    })
    .catch(error => {
        console.log(error);
        showToast('Error, actualice la página', false);
        return null;
    });
}


/********** Carga y retorna los odontologos de la BBDD en las opciones del select **********/
function cargarSelectOdontologos(idSelected) {
    const urlListarOdontologos = '/odontologo/todos';
    const settings = {
        method: 'GET'
    }

    return fetch(urlListarOdontologos, settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        const selectOdontologos = document.getElementById("selectOdontologos");

        // Recorremos la colección de odontologos del JSON
        for(odontologo of data){
            // Por cada odontologo armaremos una fila del select    
            const odontologoOption = document.createElement("option");
            odontologoOption.value = `${odontologo.id};${odontologo.matricula};${odontologo.nombre};${odontologo.apellido}`;
            odontologoOption.text = odontologo.matricula + " - " +
                odontologo.nombre + " " + 
                odontologo.apellido;

            if (idSelected == odontologo.id) 
                odontologoOption.selected = true;
            
            selectOdontologos.appendChild(odontologoOption);
        };

        // Devuelvo los odontologos cargados en la BBDD
        return data;
    })
    .catch(error => {
        console.log(error);
        showToast('Error, actualice la página', false);
        return null;
    });
}
