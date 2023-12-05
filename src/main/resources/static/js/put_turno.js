window.addEventListener('load', function () {

    insertEditModal();
    insertDeleteModal();

    // Formulario con los datos que el usuario pudo haber modificado del turno
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function(event) {
        event.preventDefault();

        // JSON con los datos del turno, enviamos el id
        //para poder identificarlo y modificarlo
        const formData = {
            id: document.querySelector('#turno_id').value,
            pacienteId: document.querySelector('#').value,
            pacienteNombre: document.querySelector('#').value,
            pacienteApellido: document.querySelector('#').value,
            pacienteCedula: document.querySelector('#').value,
            odontologoId: document.querySelector('#').value,
            odontologoMatricula: document.querySelector('#').value,
            odontologoNombre: document.querySelector('#').value,
            odontologoApellido: document.querySelector('#').value,
            fechaTurno: document.querySelector('#').value,
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
        const turno = data;
        document.querySelector('#turno_id').value = turno.id;
        document.querySelector('#matricula').value = turno.matricula;
        document.querySelector('#nombre').value = turno.nombre;
        document.querySelector('#apellido').value = turno.apellido;
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
    
        console.log("antes del fetch");

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