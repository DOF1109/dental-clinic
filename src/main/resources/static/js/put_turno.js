window.addEventListener('load', function () {

    insertEditModal();
    insertDeleteModal();

    // Variable con los datos del turno obtenidos del findBy
    let turno;

    // Formulario con los datos que el usuario pudo haber modificado del turno
    const formulario = document.querySelector('#update_turno_form');
    
    formulario.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const pacienteSeleccionado = document.getElementById("selectPacientes").value.split(';');
        const odontologoSeleccionado = document.getElementById("selectOdontologos").value.split(';');

        // JSON con los datos del turno
        const formData = {
            id: turno.id,
            pacienteId: pacienteSeleccionado[0],
            pacienteNombre: pacienteSeleccionado[1],
            pacienteApellido: pacienteSeleccionado[2],
            pacienteCedula: pacienteSeleccionado[3],
            odontologoId: odontologoSeleccionado[0],
            odontologoMatricula: odontologoSeleccionado[1],
            odontologoNombre: odontologoSeleccionado[2],
            odontologoApellido: odontologoSeleccionado[3],
            fechaTurno: document.getElementById('fechaTurno').value,
        };

        const url = '/turno';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url,settings)
        .then(response => {
            response.json()
        })
        .then(data => {
            showToast('Turno actualizado!', true);
            location.reload();
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    })

})

// Se invoca al hacer click sobre el id de un turno del listado
// Llena el formulario con los datos del turno a modificar
function findBy(id) {
    const url = '/turno/' + id;
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        turno = data;
        cargarSelectPacientes(turno.pacienteId);
        cargarSelectOdontologos(turno.odontologoId);
        // Abro el modal con el formulario y sus datos
        showModal('turnoEditModal', 'btnCancelUpdateModal');
    })
    .catch(error => {
        console.log(error);
        showToast('Error, intente nuevamente', false);
    })
}

// Se invoca al hacer click sobre el boton de eliminar de un turno del listado
function deleteBy(id) {
    // Pide confirmación para eliminar
    showModal('turnoDeleteModal', 'cancelDeleteModal');

    // Si confirma, invoco a la API para eliminar
    const deleteConfirm = document.querySelector('#deleteConfirm');
    deleteConfirm.addEventListener('click', () => {
        const url = '/turno/' + id;
        const settings = {
            method: 'DELETE'
        }

        fetch(url,settings)
        .then(response => {
            response.json()
        })
        .then(data => {
            location.reload();
            showToast('Turno eliminado', true);
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    });

}