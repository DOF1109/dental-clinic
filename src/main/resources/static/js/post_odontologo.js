window.addEventListener('load', function () {

    // Formulario donde el usuario cargará el nuevo odontologo
    const formulario = document.querySelector('#add_new_odontologo');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault()

       // JSON con los datos del odontologo
        const formData = {
            matricula: document.querySelector('#matricula').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,

        };

        const url = '/odontologo';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            // Si no hay ningun error se muestra un mensaje de carga correcta
            const successAlert = '<div class="alert alert-success alert-dismissible" role="alert">' +
                '<div>Odontologo cargado!</div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">' +
                '</button></div>'

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = "block";

            resetUploadForm();
        })
        .catch(error => {
            // Si hay algun error se muestra un mensaje para intentar nuevamente
            const errorAlert = '<div class="alert alert-danger alert-dismissible" role="alert">' +
                '<div><strong>Error, intente nuevamente</strong></div>' +
                '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close">' +
                '</button></div>'

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = "block";

            resetUploadForm();
        })
    });

    function resetUploadForm(){
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }
    
});