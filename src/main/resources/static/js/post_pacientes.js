window.addEventListener('load', function () {

    // Formulario donde el usuario cargará el nuevo paciente
    const formulario = document.querySelector('#add_new_paciente');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();

       // JSON con los datos del paciente
        const formData = {
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            cedula: document.querySelector('#cedula').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            calle: document.querySelector('#calle').value,
            numero: document.querySelector('#numero').value,
            localidad: document.querySelector('#localidad').value,
            provincia: document.querySelector('#provincia').value,
            email: document.querySelector('#email').value,
        };

        const url = '/paciente';
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
            showToast('Paciente cargado!', true);
            resetUploadForm();
        })
        .catch(error => {
            console.log(error);
            showToast('Error, intente nuevamente', false);
        })
    });

    function resetUploadForm(){
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#cedula').value = "";
        document.querySelector('#fechaIngreso').value = "";
        document.querySelector('#calle').value = "";
        document.querySelector('#numero').value = "";
        document.querySelector('#localidad').value = "";
        document.querySelector('#provincia').value = "";
        document.querySelector('#email').value = "";
    }
    
});