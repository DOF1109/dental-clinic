window.addEventListener('load', function () {

    insertEditModal();
    insertDeleteModal();

    // Formulario con los datos que el usuario pudo haber modificado del paciente
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function(event) {
        event.preventDefault();

        // JSON con los datos del paciente, enviamos el id
        //para poder identificarlo y modificarlo
        const formData = {
            id: document.querySelector('#paciente_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            },            
            email: document.querySelector('#email').value,
        };

        const url = '/paciente';
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
            showToast('Paciente actualizado!', true);
            location.reload();
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    })

})

// Se invoca al hacer click sobre el id de un paciente del listado
// Llena el formulario con los datos del paciente a modificar
function findBy(id) {
    const url = '/paciente/' + id;
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        const paciente = data;
        document.querySelector('#paciente_id').value = paciente.id;
        document.querySelector('#nombre').value = paciente.nombre;
        document.querySelector('#apellido').value = paciente.apellido;
        document.querySelector('#cedula').value = paciente.cedula;
        document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
        if(paciente.domicilio){
            document.querySelector('#calle').value = paciente.domicilio.calle;
            document.querySelector('#numero').value = paciente.domicilio.numero;
            document.querySelector('#localidad').value = paciente.domicilio.localidad;
            document.querySelector('#provincia').value = paciente.domicilio.provincia;
        }
        document.querySelector('#email').value = paciente.email;

        // Abro el modal con el formulario y sus datos
        showModal('pacienteEditModal', 'btnCancelUpdateModal');
    })
    .catch(error => {
        console.log(error);
        showToast('Error, intente nuevamente', false);
    })
}

// Se invoca al hacer click sobre el boton de eliminar de un paciente del listado
function deleteBy(id) {
    // Pide confirmación para eliminar
    showModal('pacienteDeleteModal', 'cancelDeleteModal');

    // Si confirma, invoco a la API para eliminar
    const deleteConfirm = document.querySelector('#deleteConfirm');
    deleteConfirm.addEventListener('click', () => {
        const url = '/paciente/' + id;
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
            showToast('paciente eliminado', true);
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    });

}