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
            showToast('Odontologo cargado!', true);
        })
        .catch(error => {
            showToast('Error, intente nuevamente', false);
        })
        resetUploadForm();
    });

    function resetUploadForm(){
        document.querySelector('#matricula').value = "";
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
    }
    
});