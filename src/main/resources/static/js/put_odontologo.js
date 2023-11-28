window.addEventListener('load', function () {
    // Formulario con los datos que el usuario pudo haber modificado del odontologo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let odontologoId = document.querySelector('#odontologo_id').value;

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
            // return the result of parsing the JSON
            response.json()
        })
        .then(data => {
            // do something with the data if needed
            location.reload();
        })
        .catch(error => {
            // Si hay algun error se muestra un mensaje para intentar nuevamente
            const errorAlert = '<div class="alert alert-danger alert-dismissible" role="alert">' +
                '<div><strong>Error, intente nuevamente</strong></div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">' +
                '</button></div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";
        })
    })

})

// Se invoca al hacer click sobre el id de un odontologo del listado
// Llena el formulario con los datos del odontologo a modificar
function findBy(id) {
    const url = '/odontologo'+"/"+id;
    const settings = {
        method: 'GET'
    }

    fetch(url,settings)
    .then(response => response.json())
    .then(data => {
        let odontologo = data;
        document.querySelector('#odontologo_id').value = odontologo.id;
        document.querySelector('#matricula').value = odontologo.matricula;
        document.querySelector('#nombre').value = odontologo.nombre;
        document.querySelector('#apellido').value = odontologo.apellido;
        // El formulario por default esta oculto y al editar se habilita
        document.querySelector('#div_odontologo_updating').style.display = "block";
    }).catch(error => {
        alert("Error: " + error);
    })
}

// Se invoca al hacer click sobre el boton de eiliminar de un odontologo del listado
function deleteBy(id) {
    const url = '/odontologo'+"/"+id;
    const settings = {
        method: 'DELETE'
    }

    fetch(url,settings)
    .then(response => {
        // return the result of parsing the JSON
        response.json()
    })
    .then(data => {
        // do something with the data if needed
        location.reload();
    }).catch(error => {
        alert("Error: " + error);
    })
}