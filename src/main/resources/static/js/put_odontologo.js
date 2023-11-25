window.addEventListener('load', function () {
    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado de la odontologo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

        let odontologoId = document.querySelector('#odontologo_id').value;

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //el odontologo que enviaremos en formato JSON
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
            console.log("Error: " + error);
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