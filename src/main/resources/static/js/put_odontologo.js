window.addEventListener('load', function () {

    insertEditModal();
    insertDeleteModal();

    // Formulario con los datos que el usuario pudo haber modificado del odontologo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        // JSON con los datos del odontologo, enviamos el id
        //para poder identificarlo y modificarlo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        };

        const url = '/odontologo';
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
            console.log("convierte json");
        })
        .then(data => {
            console.log("respuesta correctar");
            showToast('Odontologo actualizado!', true);
            location.reload();
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    })

})

// Se invoca al hacer click sobre el id de un odontologo del listado
// Llena el formulario con los datos del odontologo a modificar
function findBy(id) {
    const url = '/odontologo/' + id;
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json()) // si es GET va en 1 linea
    .then(data => {
        const odontologo = data;
        document.querySelector('#odontologo_id').value = odontologo.id;
        document.querySelector('#matricula').value = odontologo.matricula;
        document.querySelector('#nombre').value = odontologo.nombre;
        document.querySelector('#apellido').value = odontologo.apellido;
        // Abro el modal con el formulario y sus datos
        showModal('odontologoEditModal', 'cancelUpdateModal');
    })
    .catch(error => {
        showToast('Error, intente nuevamente', false);
    })
}

// Se invoca al hacer click sobre el boton de eliminar de un odontologo del listado
function deleteBy(id) {
    // Pide confirmación para eliminar
    showModal('odontologoDeleteModal', 'cancelDeleteModal');

    // Si confirma, invoco a la API para eliminar
    const deleteConfirm = document.querySelector('#deleteConfirm');
    deleteConfirm.addEventListener('click', () => {
        const url = '/odontologo/' + id;
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
            showToast('Odontologo eliminado', true);
        })
        .catch(error => {
            showToast('Error, intente nuevamente', false);
            console.log(error);
        })
    });

}